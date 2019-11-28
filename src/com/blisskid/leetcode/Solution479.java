package com.blisskid.leetcode;

public class Solution479 {
    public static void main(String[] args) {
        Solution479 s = new Solution479();
        System.out.println(s.largestPalindrome(5));
//        System.out.println(s.largestPalindrome1(5));
        System.out.println(s.largestPalindrome2(5));
    }

    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int maxNum = (int) Math.pow(10, n) - 1;
        long palindrome = 1;
        for (int i = maxNum; i > 0; i--) {
            //create palindrome
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            palindrome = Long.parseLong(Integer.toString(i) + sb.reverse());
            for (int j = maxNum; j > (int) Math.sqrt(palindrome); j--) {
                if (palindrome % j == 0 && Math.pow(10, n - 1) <= palindrome / j && palindrome / j < Math.pow(10, n)) {
                    System.out.println(j + " * " + palindrome / j + " = " + palindrome);
                    return (int) (palindrome % 1337);
                }
            }
        }
        return (int) palindrome % 1337;
    }

    public int largestPalindrome2(int n) {
        if(n == 1) return 9;
        //计算给定位数的最大值
        long max = (long)Math.pow(10,n) - 1;
        //从max - 1开始循环，原因见上文
        for(long i = max - 1; i > max / 10; i--){
            //1. 构造回文数
            String s1 = String.valueOf(i);
            long rev = Long.parseLong(s1 + new StringBuilder(s1).reverse().toString());
            //2. 检验该回文数能否由给定的数相乘得到
            for(long x = max; x * x >= rev; x --){

                if(rev % x == 0) {
                    System.out.println(x + " * " + rev / x + " = " + rev);
                    return (int)(rev % 1337);
                }
            }
        }
        return -1;
    }

    public int largestPalindrome1(int n) {
        long maxPali = 1;
        int maxNum = 0, tempi = 0, tempj = 0;
        int minNum = (int) Math.pow(10, n - 1) * 9;
        for (int i = 0; i < n; i++) {
            maxNum += (long) Math.pow(10, i) * 9;
        }

        for (int i = maxNum; i > minNum; i--) {
            for (int j = maxNum; j > minNum; j--) {
                long l = i * j;
                if (isPalindrome(l) && maxPali < l) {
                    maxPali = l;
                    tempi = i;
                    tempj = j;
                }
            }
        }
        System.out.println(tempi + " * " + tempj + " = " + maxPali);
        return (int) maxPali % 1337;
    }

    private boolean isPalindrome(long l) {
        String lStr = Long.toString(l);
        for (int i = 0, j = lStr.length() - 1; i < j; i++, j--) {
            if (lStr.charAt(i) != lStr.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
