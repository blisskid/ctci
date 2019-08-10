package com.blisskid.leetcode;

import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("Absfds"));
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int count = 0;
        int res = 0;
        int index = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        while (index < s.length()) {
            Character c = s.charAt(index);
            if (!map.containsKey(c)) {
                map.put(c, index);
                count++;
                index++;
            } else {
                count = 0;
                index = map.get(c) + 1;
                map = new HashMap<Character, Integer>();
            }
            res = count > res ? count : res;
        }

        return res;
    }
}