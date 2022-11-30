package com.coding_challenge;
import java.util.*;
/**
You've decided to make an advanced version of a variant of the game Mahjong, incorporating runs.

Players have a number of tiles, each marked 0-9. The tiles can be grouped into pairs or triples of the same tile or runs.

A run is a series of three consecutive tiles, like 123, 678, or 456. 0 counts as the lowest tile, so 012 is a valid run, but 890 is not.

A complete hand consists of exactly one pair, and any number (including zero) of triples and/or three-tile runs. Each tile is used in exactly one triple, pair, or run.

Write a function that returns true or false depending on whether or not the collection represents a complete hand under these rules.

hand1 = "11123"          # True. 11 123
hand2 = "12131"          # True. Also 11 123. Tiles are not necessarily sorted.
hand3 = "11123455"       # True. 111 234 55
hand4 = "11122334"       # True. 11 123 234
hand5 = "11234"          # True. 11 234
hand6 = "123456"         # False. Needs a pair
hand7 = "11133355577"    # True. 111 333 555 77
hand8 = "11223344556677" # True. 11 234 234 567 567 among others
hand9 = "12233444556677" # True. 123 234 44 567 567
hand10 = "11234567899"   # False.
hand11 = "00123457"      # False.
hand12 = "0012345"       # False. A run is only three tiles
hand13 = "11890"         # False. 890 is not a valid run
hand14 = "99"            # True.
hand15 = "111223344"     # False.

All Test Cases
advanced(hand1)  => True
advanced(hand2)  => True
advanced(hand3)  => True
advanced(hand4)  => True
advanced(hand5)  => True
advanced(hand6)  => False
advanced(hand7)  => True
advanced(hand8)  => True
advanced(hand9)  => True
advanced(hand10) => False
advanced(hand11) => False
advanced(hand12) => False
advanced(hand13) => False
advanced(hand14) => True
advanced(hand15) => False

Complexity Variable
N - Number of tiles in the input string
 */

public class Mahjong {
    public static boolean check(String input) {
        // 1. count
        // "12355588" => {1->1, 2->1, 3->1, 5->3, 8->2}
        Map<Integer, Integer> counts = new HashMap<>();
        for (char c : input.toCharArray()) {
            int num = c - '0';
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        System.out.println(counts);

        // 2. back-tracking to check
        return backtracking(counts);
    }

    public static boolean backtracking(Map<Integer, Integer> counts) {
        // threes + one pair，直接返回true
        if (isThreeAndPair(counts)) {
            return true;
        }

        // back-tracking,(0, 1, 2), (1, 2, 3), (2, 3, 4), ..., (7, 8, 9)
        for (int i=0; i<8; i++) {
            int count1 = counts.getOrDefault(i, 0);
            int count2 = counts.getOrDefault(i + 1, 0);
            int count3 = counts.getOrDefault(i + 2, 0);
            if (count1 > 0 && count2 > 0 && count3 > 0) {
                // straight, continue to check
                counts.put(i, count1 - 1);
                counts.put(i + 1, count2 - 1);
                counts.put(i + 2, count3 - 1);
                if (backtracking(counts)) {
                    return true;
                }
                // back-tracking, restore the status
                counts.put(i, count1);
                counts.put(i + 1, count2);
                counts.put(i + 2, count3);
            }
        }

        // tried every possible, not valid
        return false;
    }

    public static boolean isThreeAndPair(Map<Integer, Integer> counts) {
        boolean foundOnePair = false;
        for (int key : counts.keySet()) {
            int count = counts.get(key);
            if (count % 3 == 1) {
                return false; // single
            } else if (count % 3 == 2) {
                if (foundOnePair) {
                    return false; // two pairs
                } else {
                    foundOnePair = true;
                }
            } else {
                // pass, count % 3 == 0, threes
            }
        }

        return foundOnePair; // must have one pair
    }

    public static void test() {
        String input = "1283855";
        System.out.println("check: " + input + " : " + Mahjong.check(input));
//
//        input = "123555";
//        System.out.println("check: " + input + " : " + Mahjong.check(input));
    }
}
