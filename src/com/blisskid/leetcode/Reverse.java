package com.blisskid.leetcode;

public class Reverse {

    public int reverse(int x) {
        int sign = (x < 0) ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (res > 0 && (sign == 1 && ((Integer.MAX_VALUE - x % 10) / res) < 10 || sign == -1 && (Integer.MIN_VALUE + x % 10) / res > -10)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return sign * res;
    }

}
