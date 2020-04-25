package com.blisskid.leetcode.other;
import java.util.*;

public class S1394 {

    public static void main(String[] args) {
        S1394 s = new S1394();
        int[] arr = {2,2,3,4};
        System.out.println(s.findLucky(arr));
    }

    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        Integer result = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == entry.getValue()) {
                if (entry.getKey() > result) {
                    result = entry.getKey();
                }
            }
        }

        return result;
    }
}
