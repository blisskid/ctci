package com.blisskid.leetcode;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome(2134328499));
        String str = new String("ieiwfkslfls");
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0, temp = x;
        while (temp > 0) {
            if (res > 0 && ((Integer.MAX_VALUE - temp % 10) / res) < 10) {
                return false;
            }
            res = 10 * res + temp % 10;
            temp = temp / 10;
        }
        if (x == res) {
            return true;
        } else {
            return false;
        }
    }
}
