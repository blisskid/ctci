package com.blisskid.leetcode;

public class Palindromic {

    public static void main(String[] args) {
        Palindromic palindromic = new Palindromic();
        System.out.println(palindromic.longestPalindrome("cbbd"));
    }



    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 1; i < s.length(); i++) {
            int leftIndex = i, rightIndex = i;
            while (leftIndex >= 0 && s.charAt(leftIndex) == s.charAt(i)) {
                if ((i - leftIndex) > (end - start)) {
                    start = leftIndex;
                    end = i;
                }
                leftIndex--;
            }
            while (rightIndex < s.length() && s.charAt(rightIndex) == s.charAt(i)) {
                if ((rightIndex - i) > (end - start)) {
                    start = i;
                    end = rightIndex;
                }
                rightIndex++;
            }
            while (leftIndex >= 0 && rightIndex < s.length() && s.charAt(leftIndex) == s.charAt(rightIndex)) {
                if ((rightIndex - leftIndex) > (end - start)) {
                    start = leftIndex;
                    end = rightIndex;
                }
                leftIndex--;
                rightIndex++;
            }
        }
        return s.substring(start, end + 1);
    }

    public String longestPalindrome1(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (checkPalindrome(s.substring(i, j))) {
                    if ((j - i) > (end - start)) {
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end);
    }

    public boolean checkPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
