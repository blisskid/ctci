package com.blisskid.leetcode.hashmap;
import java.util.*;

class MyHashMap {

    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(3));
        hashMap.put(2, 1);
        System.out.println(hashMap.get(2));
        hashMap.remove(2);
        System.out.println(hashMap.get(2));
    }

    class Pair <k, v> {
        private k key;
        private v value;

        public Pair(k key, v value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Pair>[] listArr;

    /** Initialize your data structure here. */
    public MyHashMap() {
        listArr = new List[2069];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % 2069;
        List<Pair> list = listArr[index];
        if (list == null) {
            list = new ArrayList();
            list.add(new Pair<Integer, Integer>(key, value));
            listArr[index] = list;
        } else {
            int count = 0;
            for (Pair<Integer, Integer> p : list) {
                if (p.key == key) {
                    p.value = value;
                    break;
                }
                count++;
            }
            if (count == list.size()) {
                list.add(new Pair(key, value));
            }
        }

    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % 2069;
        List<Pair> list = listArr[index];
        if (list != null) {
            for (Pair<Integer, Integer> p : list) {
                if (p.key == key) {
                    return p.value;
                }
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % 2069;
        List<Pair> list = listArr[index];
        if (list != null) {
            for (Pair<Integer, Integer> p : list) {
                if (p.key == key) {
                    list.remove(p);
                    return;
                }
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
