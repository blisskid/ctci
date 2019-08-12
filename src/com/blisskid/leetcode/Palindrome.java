package com.blisskid.leetcode;

public class Palindrome {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0;
        while (x > 0) {
            if (res > 0 && ((Integer.MAX_VALUE - x % 10) / res) < 10) {
                return false;
            }
            res = 10 * res + x % 10;
            x = x / 10;
        }
        if (x == res) {
            return true;
        } else {
            return false;
        }
    }
}
