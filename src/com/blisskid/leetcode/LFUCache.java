package com.blisskid.leetcode;
import java.util.*;
/*
1:use a map to store key and value
2:use a map to store key and freq,use freq to be the key and value is list of values
?how to remove a key from the value list in O(1)'s time complexity?use linkedHashSet
*/
class LFUCache {
    public static void main(String[] args) {
        LFUCache cache=new LFUCache(3);
        cache.put(2,2);
        cache.put(1,1);
        cache.get(2);
        cache.get(1);
        cache.get(2);
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(3));
        cache.get(2);
        cache.get(1);
        cache.get(4);
    }

    private HashMap<Integer,Integer> valMap;
    private TreeMap<Integer,LinkedHashSet<Integer>> freqMap;  //key is freq,value is the key's list
    private HashMap<Integer,Integer> keyMap;    //key is the key,value is the freq
    private int size;
    private int capacity;

    public LFUCache(int capacity) {
        valMap=new HashMap();
        freqMap=new TreeMap();
        keyMap=new HashMap();
        size=0;
        this.capacity=capacity;
    }

    public int get(int key) {
        if(valMap.containsKey(key)){
            addFreq(key);
            return valMap.get(key);
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        //key existed
        if(valMap.containsKey(key)){
            valMap.put(key,value);
            addFreq(key);
        }else{
            //add a new one
            if(size==capacity){
                removeLFU();
            }
            addNew(key,value);
        }
    }

    //freq+1,remove the key from the current freq's list,put the key in the freq+1's list
    private void addFreq(int key){
        //get the current freq
        int freq=keyMap.get(key);
        int value=valMap.get(key);
        //get the current freq's key list and remove
        LinkedHashSet<Integer> keySet=freqMap.get(freq);
        keySet.remove(key);
        if(keySet.isEmpty()){
            freqMap.remove(freq);
        }
        int newFreq=freq+1;
        LinkedHashSet<Integer> newKeySet;
        if(freqMap.containsKey(newFreq)){
            newKeySet=freqMap.get(newFreq);
        }else{
            newKeySet=new LinkedHashSet();
        }
        newKeySet.add(key);
        freqMap.put(newFreq,newKeySet);
        keyMap.put(key,newFreq);
        //add new freq
    }

    private void addNew(int key,int value){
        valMap.put(key,value);
        LinkedHashSet<Integer> freqOne;
        if(freqMap.containsKey(1)){
            freqOne=freqMap.get(1);
        }else{
            //no freq = 1
            freqOne=new LinkedHashSet();
        }
        freqOne.add(key);
        freqMap.put(1,freqOne);
        keyMap.put(key,1);
        size++;
    }

    private void removeLFU(){
        //get the first one from freqMap
        if(!freqMap.isEmpty()){
            int deleteSetKey=freqMap.keySet().iterator().next();
            LinkedHashSet<Integer> deleteValue=freqMap.get(deleteSetKey);
            Integer delKey=deleteValue.iterator().next();
            deleteValue.remove(delKey);
            if(deleteValue.isEmpty()){
                freqMap.remove(deleteSetKey);
            }
            valMap.remove(delKey);
            keyMap.remove(delKey);
            size--;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
