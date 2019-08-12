package com.blisskid.leetcode;

public class MyAtoi {
    public static void main(String[] args) {
        System.out.println(MyAtoi.myAtoi("+1"));
    }

    public static int myAtoi(String str) {
        //remove whitespace
        str = str.trim();
        //check str
        if ("" == str || str.length() == 0) {
            return 0;
        }
        int sign = 1;
        if (str.charAt(0) == '-') {
            sign = -1;
        } else if (str.charAt(0) == '+') {
            sign = 1;
        } else if (str.charAt(0) < '0' || str.charAt(0) > '9') {
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if ( i == 0 && (str.charAt(0) == '-' || str.charAt(0) == '+')) {
                continue;
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                sb.append(str.charAt(i));
            } else {
                break;
            }
        }
        int res = 0;
        for (int i = 0; i < sb.length(); i++) {
            int temp = sb.charAt(i) - '0';
            if (res > 0) {
                if (sign == 1 && ((Integer.MAX_VALUE - temp) / res) < 10) {
                    return Integer.MAX_VALUE;
                }
                if (sign == -1 && (Integer.MIN_VALUE + temp) / res > -10) {
                    return Integer.MIN_VALUE;
                }
            }
            res = res * 10 + temp;
        }
        return sign * res;
    }
}
