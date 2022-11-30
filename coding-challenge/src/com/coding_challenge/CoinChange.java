package com.coding_challenge;

import java.util.*;

public class CoinChange {
    static Set<Map<Integer, Integer>> comps;
    static Map<Integer, Integer> path;
    static Map<String, List<Map<Integer, Integer>>> memo;
    static boolean found;

    static int coinLimit = 3;

    public static List<Map<Integer, Integer>> dfs(int[] coins, int start, int total) {
        List<Map<Integer, Integer>> ret = new ArrayList<>();
        if (total == 0) {
            ret.add(new HashMap<>());
            return ret;
        }

        String key = String.valueOf(start) + "+" + String.valueOf(total);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (int i=start; i<coins.length; i++) {
            int coin = coins[i];
            if (total - coin >= 0) {
                List<Map<Integer, Integer>> next = dfs(coins, i, total - coin);
                for (Map<Integer, Integer> coinsCount : next) {
                    // add this coin to the combinations
                    Map<Integer, Integer> thisCoinsCount = new HashMap<>(coinsCount);
                    thisCoinsCount.put(coin, thisCoinsCount.getOrDefault(coin, 0) + 1);
                    ret.add(thisCoinsCount);
                }
            }
        }

        memo.put(key, ret);
        return ret;
    }

    public static int minCoin(int[] coins, int total) {
        // dp(i): min total number of coins that sum up to total
        // dp(0) = 0;  no coins sum up to total, 0;
        // dp(i): for coins[i] in coins, if dp(i - coins[i]) + 1 < dp(i) => dp(i) = dp(i - coins[i])
        int[] dp = new int[total + 1];
        dp[0] = 0;

        Map<Integer, Integer>[] counts = new Map[total + 1];
        for (int i=0; i<=total; i++) {
            counts[i] = new HashMap<>();
        }

        for (int i=1; i<=total; i++) {
            dp[i] = total + 1; // invalid ways
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] + 1 < dp[i]) {
                    // add this coin
                    counts[i] = new HashMap<>(counts[i - coin]);
                    counts[i].put(coin, counts[i].getOrDefault(coin, 0) + 1);

                    dp[i] = dp[i - coin] + 1;
                }
            }

            if (dp[i] != total + 1) {
                System.out.println("total : " + i + " => min number: " + dp[i] + " : " + counts[i]);
            }
        }

        System.out.println(counts[total]);
        return dp[total] == total + 1 ? -1 : dp[total];
    }

    // validate the coin combination
    public static boolean validate(int totalCoins) {
        return totalCoins >= coinLimit;
    }

    public static void test() {
        // write your code here
        System.out.println("java.version: " + System.getProperty("java.version"));
        int[] coins = {1, 5, 10, 25};
        int total = 76;

        // List[map {coin -> count}]
        comps = new HashSet<>();
        path = new HashMap<>();
        memo = new HashMap<>();

        List<Map<Integer, Integer>> result = dfs(coins, 0, total);
        List<Map<Integer, Integer>> filter = new ArrayList<>();
        for (Map<Integer, Integer> way : result) {
            if (way.size() < 4 && count(way) < 5) {
                filter.add(way);
            }
        }

        System.out.println(filter);
    }

    public static void test2() {
//        int[] coins = {1, 5, 10, 25};
//        int total = 76;

        int[] coins = {1, 3, 5, 25};
        int total = 76;

        int minWays = minCoin(coins, total);

        System.out.println("min: " + minWays);
    }
    public static int count(Map<Integer, Integer> way) {
        int total = 0;
        for (int count : way.values()) {
            total += count;
        }

        return total;
    }
}
