package com.coding_challenge;
import java.util.*;

public class MostFreq {
    // follow up - OOD
    Map<String, Map<String, Integer>> count;
    public void MostFreq() {
        this.count = new HashMap<>();
    }

    public void addStrings(String[] strs) {
        int n = strs.length;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                String str1 = strs[i];
                String str2 = strs[j];
                // str1 -> { str1 : count + 1}
                // str2 -> { str1 : count + 1}
                addRelation(str1, str2, count);
                addRelation(str2, str1, count);
            }
        }
    }

    public Map<String, List<String>> output() {
        Map<String, List<String>> out = new HashMap<>();
        for (String key : count.keySet()) {
            List<String> list = findMaxFreq(count.get(key));
            out.put(key, list);
        }

        return out;
    }

    //<== follow up - OOD

    public static void test() {
        String[][] input = {
                {"Casper", "Purple", "Wayfair"},
                {"Purple", "Wayfair", "Tradesy"},
                {"Wayfair", "Tradesy", "Peloton"}
        };

        //  {Tradesy=[Tradesy], Purple=[Wayfair], Peloton=[Tradesy, Peloton], Casper=[Purple, Wayfair], Wayfair=[Wayfair]}

        // loop the strings
        // for every pair in the string list, use a hashmap to count the frequency
        // Casper -> { Purple : 1, Wayfair: 1}
        // Purple -> { Casper : 1, Wayfair: 2 }
        Map<String, Map<String, Integer>> count = new HashMap<>(); // {string, {other string -> freq}}
        for (String[] strs : input) {
            int n = strs.length;
            for (int i=0; i<n; i++) {
                for (int j=i+1; j<n; j++) {
                    String str1 = strs[i];
                    String str2 = strs[j];
                    // str1 -> { str1 : count + 1}
                    // str2 -> { str1 : count + 1}
                    addRelation(str1, str2, count);
                    addRelation(str2, str1, count);
                }
            }
        }

        // then check the hashmap to get the max freq strings of each string
        Map<String, List<String>> out = new HashMap<>();
        for (String key : count.keySet()) {
            List<String> list = findMaxFreq(count.get(key));
            out.put(key, list);
        }

        System.out.println(out);
    }
    private static List<String> findMaxFreq(Map<String, Integer> freq) {
        // { Casper : 1, Wayfair: 2, Joe : 3, Doe: 2 } => return [Wayfair, Doe]
        List<String> res = new ArrayList<>();
        int maxfreq = 0;
        for (String key : freq.keySet()) {
            int curFreq = freq.get(key);
            if (maxfreq < curFreq) {
                maxfreq = curFreq;
                res = new ArrayList<>();
                res.add(key);
            } else if (maxfreq == curFreq) {
                res.add(key);
            }
        }

        return res;
    }

    private static void addRelation(String src, String target, Map<String, Map<String, Integer>> count) {
        // src -> {target : count + 1}
        Map<String, Integer> freq = count.computeIfAbsent(src, k -> new HashMap<>());
        freq.put(target, freq.getOrDefault(target, 0) + 1);
    }
}
