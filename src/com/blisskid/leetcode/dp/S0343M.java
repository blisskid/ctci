package com.blisskid.leetcode.dp;

public class S0343M {

    public static void main(String[] args) {
        System.out.println(new S0343M().integerBreak(6));
    }

    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int[] f = new int[n + 1];
        f[2] = 1;
        f[3] = 2;
        f[4] = 4;
        for (int i = 5; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 2; j <= i / 2; j++) {
                max = Math.max(Math.max(f[j], j) * Math.max(f[i - j], i - j), max);
            }
            f[i] = max;
        }
        return f[n];
    }


}
