package com.coding_challenge;

import java.util.*;

public class MyArrayList<E> {
    private static int DEFAULT_CAPACITY = 10;
    int size;
    Object[] arr;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        this.arr = new Object[capacity];
        this.size = 0;
    }


    public boolean add(E val) {
        ensureCapcity(size + 1);
        arr[size++] = val;
        return true;
    }


    // 这个函数的主要作用就是确保数组的长度至少为 size + 1
    private void ensureCapcity(int size) {
        // make sure arr.length > size
        if (arr.length <= size) {
            grow(DEFAULT_CAPACITY);
        }
    }
    /**
     * 该函数主要保证 elementData 的长度至少为 minCapacity
     * 如果数组的长度小于 minCapacity 则需要进行扩容，反之
     */
    private void grow(int minCapacity) {
        int oldCapacity = arr.length;
        // 新的数组长度为原来数组长度的两倍
        int newCapacity = oldCapacity * 2;

        // 如果数组新数组的长度 newCapacity 小于所需要的长度 minCapacity
        // 新申请的长度应该为 minCapacity
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        // 申请一个长度为 newCapacity 的数组，在将原来数组
        // elementData 的数据拷贝到新数组当中
        arr = Arrays.copyOf(arr, newCapacity);
    }

    public E get(int index) {
        if (index >= size) {
            return null;
        } else {
            return (E)arr[index];
        }
    }

    public boolean set(int index, E val) {
        if (index >= size) {
            return false;
        } else {
            arr[index] = val;
            return true;
        }
    }

    public boolean remove (E val) {
        if (val == null) {
            for (int i=0; i<size; i++) {
                if (arr[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i=0; i<size; i++) {
                if (arr[i].equals(val)) {
                    remove(i);
                    return true;
                }
            }
        }

        // not found
        return false;
    }
    public boolean remove(int index) {
        // move every element from [index + 1, ... size - 1] to [index, ... size - 2]
        if (index >= size) {
            return false;
        }

        for (int i=index + 1; i<size; i++) {
            arr[i-1] = arr[i];
        }

        arr[size--] = null; // 别忘了!
        return true;
    }
    public static void test() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(-i);
        }

        System.out.println(list);
        list.remove(Integer.valueOf(-6));
        System.out.println(list);
        System.out.println(list.arr.length);
        list.add(99999);
        System.out.println(list);
    }
}
