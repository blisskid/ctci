package com.blisskid.leetcode.other;

import java.util.HashSet;

public class LongestSubstring {

    public static void main(String[] args) {
        LongestSubstring solution = new LongestSubstring();
        System.out.println(solution.longestSubstring("pwwkew"));
    }


    public int longestSubstring(String s) {
        int i = 0, j = 0, res = 0, n = s.length();
        HashSet set = new HashSet();
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

}
