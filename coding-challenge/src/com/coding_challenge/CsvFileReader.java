package com.coding_challenge;

import java.io.*;
import java.net.URL;
import java.util.*;

public class CsvFileReader {
    private static final String CSV_SPLIT = ",";

    public static CsvFile readFile(String fileName) throws IOException {
        URL path = Main.class.getResource(fileName);
        File f = new File(path.getFile());
        BufferedReader fileReader = new BufferedReader(new FileReader(f));
        String line = fileReader.readLine();
        if (line == null) {
            return null;
        }

        // read columns
        CsvFile file = new CsvFile(fileName);
        file.setColumns(Arrays.asList(line.split(CSV_SPLIT)));

        // read content
        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(CSV_SPLIT);
            file.appendLine(tokens);;
        }

        return file;
    }

    public static void testFunc() {
        // outer join all the files by same column name
        // file1:         file2:        file3           =>  joined file
        // ID Name        ID  Like        ID Salary     ID  Name  Like       Salary
        // 1  John        1   Hiking      2  2000       1   John   Hiking    NULL
        // 2  Jane        1   Swimming    4  3000       1   John   Swimming  NULL
        // 3  Smith       2   Gaming                    2   Jane   Gaming    2000
        //                                              3   Smith  NULL      NULL
        //                                              4   NULL   NULL      NULL
        try {
            CsvFile file1 = CsvFileReader.readFile("test1.csv");
            file1.print();

            CsvFile file2 = CsvFileReader.readFile("test2.csv");
            file2.print();

            System.out.println("joined file");
            file1.createIndex("ID");
            file2.createIndex("ID");
            CsvFile joinFile = file1.join(file2, "ID", "join1and2");
            joinFile.print();
//
            CsvFile file3 = CsvFileReader.readFile("test3.csv");
            file3.print();

            joinFile = joinFile.join(file3, "ID", "join1and2and3");
            joinFile.print();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
