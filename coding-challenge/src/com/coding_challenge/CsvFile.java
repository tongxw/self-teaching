package com.coding_challenge;

import java.util.*;

public class CsvFile {
    int colSize;
    List<String> columns;
    List<List<String>> lines;
    String name;

    // 优化：index
    Map<String, List<List<String>>> indexes; // [col name_col value] -> results

    public CsvFile(String filename) {
        this.name = filename;
        this.colSize = 0;
        this.columns = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.indexes = new HashMap<>();
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
        this.colSize = columns.size();
    }

    public void appendLine(String[] lineTokens) {
        if (lineTokens.length != colSize) {
            System.out.println("wrong format in this line: " + Arrays.toString(lineTokens));
            return;
        }

        appendLine(Arrays.asList(lineTokens));
    }

    public void appendLine(List<String> line) {
        if (line.size() != colSize) {
            System.out.println("wrong format in this line: " + line);
            return;
        }

        lines.add(line);
    }

    public void createIndex(String colName) throws Exception {
        int idx = columns.indexOf(colName);
        if (idx == -1) {
            throw new Exception("Colmun: " + colName + " does not exist.");
        }

        for (List<String> line : lines) {
            String key = columns.get(idx) + "#" + line.get(idx);
            indexes.computeIfAbsent(key, k -> new ArrayList<>()).add(line);
        }
    }

    // return all lines where line[col] = value
    // if not found, return empty line ["NULL", "NULL", ...]
    public List<List<String>> find(String colName, String value) throws Exception {
        int idx = columns.indexOf(colName);
        if (idx == -1) {
            throw new Exception("Colmun: " + colName + " does not exist.");
        }

        String key = colName + "#" + value;
        if (indexes.containsKey(key)) {
            return indexes.get(key);
        }

        List<List<String>> results = new ArrayList<>();
        for (int i=0; i<lines.size(); i++) {
            List<String> line = lines.get(i);
            String val = line.get(idx);
            if (val.equals(value)) {
                results.add(line);
            }
        }

        if (!results.isEmpty()) {
            indexes.computeIfAbsent(key, k -> new ArrayList<>()).addAll(results);
        }

        return results;
    }

    // return ["NULL", "NULL", ... ] match the col size
    private List<String> emptyLine() {
        List<String> line = new ArrayList<>();
        for (int i=0; i<colSize; i++) {
            line.add("NULL");
        }

        return line;
    }

    // merge target to src
    // ["1", "Joe"] + ["Hiking", "1"] => ["1", "Joe", "Hiking"]
    public static List<String> mergeLines(List<String> src, List<String> target, int srcIdx, int targetIdx, boolean overwrite) {
        List<String> result = new ArrayList<>(src);
        for (int i=0; i<target.size(); i++) {
            if (i == targetIdx) {
                if (overwrite) {
                    // overwrite src with target
                    result.set(srcIdx, target.get(i));
                }
                // else skip
            } else {
                result.add(target.get(i));
            }
        }

        return result;
    }

    // file:          other:              =>  joined file
    // ID Name        ID  Like            ID  Name  Like
    // 1  John        1   Hiking          1   John   Hiking
    // 2  Jane        1   Swimming        1   John   Swimming
    // 3  Smith       2   Gaming          2   Jane   Gaming
    //                4   Cooking         3   Smith  NULL
    //                                    4   NULL   Cooking
    public CsvFile join(CsvFile other, String colName, String joinedFileName) throws Exception {
        int idx = this.columns.indexOf(colName);
        int otherIdx = other.columns.indexOf(colName);
        if (idx == -1 || otherIdx == -1) {
            throw new Exception("Colmun: " + colName + " does not exist.");
        }

        CsvFile joinedFile = new CsvFile(joinedFileName);

        // joined colmuns
        List<String> joinedCols = CsvFile.mergeLines(this.columns, other.columns, idx, otherIdx, false);
        joinedFile.setColumns(joinedCols);

        // from this to other
//        other.startFinding();
        for (List<String> line : this.lines) {
            String value = line.get(idx);
            List<List<String>> findLines = other.find(colName, value);
            if (findLines.isEmpty()) {
                // add [NULL, NULL...] from other file
                findLines.add(other.emptyLine());
            }
            // this : {1, John}
            // other: [{1, Hiking}, {1, Swimming}] or [{"NULL"}]
            for (List<String> findLine : findLines) {
                List<String> joinedLine = CsvFile.mergeLines(line, findLine, idx, otherIdx, false);
                joinedFile.appendLine(joinedLine);
            }
        }

        // from other to this (empty line)
        for (List<String> otherLine: other.lines) {
            String value = otherLine.get(otherIdx);
            List<List<String>> findLines = this.find(colName, value);
            if (findLines.isEmpty()) {
                // join with empty line from this
                List<String> joinedLine = CsvFile.mergeLines(this.emptyLine(), otherLine, idx, otherIdx, true);
                joinedFile.appendLine(joinedLine);
            }
        }

        return joinedFile;
    }

    public void print() {
        System.out.println("---" + name + "---");
        System.out.println(columns);
        for (List<String> line : lines) {
            System.out.println(line);
        }
        System.out.println("---END---");
    }
}
