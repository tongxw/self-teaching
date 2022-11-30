package com.coding_challenge;

import java.util.*;

public class ShortestString {

    public static void test() {
        String[] input = {"cheapair", "cheapoair", "peloton", "pelican"};

        // use a map to save all the substring, {substring -> the first index of the string that contains the substring
        // use a set to save all unique sub strings
        Map<String, Integer> map = new HashMap<>(); // {substring -> the index of the string}
        Set<String> uniqueSubs = new HashSet<>(); // unique substring


        // loop: for str[i] in the list
        //      get all sub string of str[i],
        //          if sub string does not in the map => first see it => save to the map { substring -> i }
        //              => also save it the unique set of sub strings
        //          else the sub string already in the map => remove the substring from the unique set
        for (int idx=0; idx<input.length; idx++) {
            String str = input[idx];
            int len = str.length();
            for (int i=0; i<len; i++) {
                for (int j=i; j<=len; j++) {
                    String subStr = str.substring(i, j);
                    if (!map.containsKey(subStr)) {
                        map.put(subStr, idx);
                        uniqueSubs.add(subStr);
                    } else {
                        uniqueSubs.remove(subStr);
                    }
                }
            }
        }

        // check all unique strings in set, find the orignal string index from the map
        // get the shortest unique string
        Map<String, String> out = new HashMap<>(); // {str -> shortest unique str}
        for (String substr : uniqueSubs) {
            int idx = map.get(substr);
            String str = input[idx];
            if (out.containsKey(str)) {
                int curLen = out.get(str).length();
                if (curLen > substr.length()) {
                    // update the result
                    out.put(str, substr);
                }
            } else {
                out.put(str, substr);
            }
        }

        System.out.println(out);

    }
}
