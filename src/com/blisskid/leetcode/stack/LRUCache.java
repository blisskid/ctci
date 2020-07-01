package com.blisskid.leetcode.stack;
import java.util.*;
class LRUCache {

    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(1, 1);
        l.put(2, 2);
        l.get(1);
        l.put(3, 3);
        l.get(2);
        l.put(4, 4);
        l.get(1);
        l.get(3);
        l.get(4);
    }

    private Map<Integer, Obj> cache;
    private int cap;

    private class Obj {
        private int value;
        private int fre;

        public Obj(int value) {
            this.value = value;
            this.fre = Integer.MAX_VALUE;
        }
    }

    public LRUCache(int capacity) {
        cache = new HashMap();
        this.cap = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            //update fre
            for (Map.Entry<Integer, Obj> entry : cache.entrySet()) {
                if (entry.getKey() == key) {
                    entry.getValue().fre = Integer.MAX_VALUE;
                } else {
                    entry.getValue().fre--;
                }
            }
            Obj obj = cache.get(key);
            return obj.value;
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (cache.size() >= cap) {
                //cache is full, delete the least fre
                Obj del = new Obj(0);
                int delKey = 0;
                for (Map.Entry<Integer, Obj> entry : cache.entrySet()) {
                    if (entry.getValue().fre < del.fre) {
                        del = entry.getValue();
                        key = entry.getKey();
                    }
                }
                cache.remove(key);
            }
            //add ele
            Obj obj = new Obj(value);
            cache.put(key, obj);
            //update fre
            for (Map.Entry<Integer, Obj> entry : cache.entrySet()) {
                if (entry.getKey() != key) {
                    entry.getValue().fre--;
                }
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
