package com.coding_challenge;

import java.util.*;

public class RandomStore {
    // hashmap { key -> value }
    // list { key } // random(0...list.size) -> i -> get key by i
    // hashmap {key -> index of list}
    Map<String, String> data;
    List<String> keys;
    Map<String, Integer> keyToIndex;

    List<String> values;
    Map<String, Integer> valueToIndex;

    public RandomStore() {
        this.data = new HashMap<>();
        this.keys = new ArrayList<>();
        this.keyToIndex = new HashMap<>();

        // follow up: values can be duplicated
        this.values = new ArrayList<>();
        this.valueToIndex = new HashMap<>();
    }


    public String get(String key) {
        return data.getOrDefault(key, null);
    }

    public void put(String key, String value) {
        if (!data.containsKey(key)) {
            // new key, add to the key list
            keys.add(key);
            keyToIndex.put(key, keys.size() - 1);
        } else {
            // old key, remove the old value from values
            String oldValue = data.get(key);
            if (!oldValue.equals(value)) {
                removeO1(oldValue, values, valueToIndex);
            }
        }

        if (!valueToIndex.containsKey(value)) {
            values.add(value);
            valueToIndex.put(value, values.size() - 1);
        }

        data.put(key ,value);
    }

    public String remove(String key) {
        if (!data.containsKey(key)) {
            return null;
        }

        // remove it from map
        String value = data.remove(key);

        removeO1(key, keys, keyToIndex);

        // values: (follow up question)
        removeO1(value, values, valueToIndex);

        return value;
    }

    public void removeO1(String str, List<String> strList, Map<String, Integer> strIndexes) {
        int idx = strIndexes.get(str);
        // get the index of the key
        int lastIdx = strList.size() - 1;
        if (idx == lastIdx) {
            // remove the last index O(1)
            strList.remove(str);
            strIndexes.remove(idx);
        } else {
            // swap the last key to this key's index
            String lastValue = strList.get(lastIdx);
            strList.set(idx, lastValue);

            //update the key->index map
            strIndexes.put(lastValue, idx); // <_ 这句更新千万别忘了！！

            // now delete the last index O(1) and the key from keyToIndex
            strList.remove(lastIdx);
            strIndexes.remove(str);
        }
    }

    public String getRandomValue() {
        Random rand = new Random();
        int idx = rand.nextInt(keys.size());

        String key = keys.get(idx);
        return data.get(key);
    }

    public String getRandomUniqueValue() {
        Random rand = new Random();
        int idx = rand.nextInt(values.size());

        return values.get(idx);
    }


    public static void test() {
        RandomStore store = new RandomStore();
        store.put("a", "apple");
        store.put("b", "banana");
        store.put("c", "peach");
        store.put("d", "pear");


        store.remove("c");
        store.remove("a");

//        store.put("c", "apple");
//        store.put("d", "apple");

        Map<String, Integer> count = new HashMap<>();
        for (int i=0; i<4000; i++) {
            String val = store.getRandomValue();
            count.put(val, count.getOrDefault(val, 0) + 1);
        }
        System.out.println(count);

//        count.clear();
//        for (int i=0; i<4000; i++) {
//            String val = store.getRandomValue();
//            count.put(val, count.getOrDefault(val, 0) + 1);
//        }

//        System.out.println(count);
    }
}
