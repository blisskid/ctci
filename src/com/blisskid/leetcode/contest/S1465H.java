package com.blisskid.leetcode.contest;
import java.math.BigInteger;
import java.util.*;

public class S1465H {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxV = 0;
        int maxH = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        //h is cutted into i + 1 by horizontalCuts
        //w is cutted into .. by verticalCuts
        for (int i = 0; i <= horizontalCuts.length; i++) {
            int verticalDistance = 0;
            if (i == 0) verticalDistance = horizontalCuts[i];
            else if (i == horizontalCuts.length) verticalDistance = h - horizontalCuts[i - 1];
            else {
                verticalDistance = horizontalCuts[i] - horizontalCuts[i - 1];
            }
            maxV = Math.max(maxV, verticalDistance);
        }
        for (int j = 0; j <= verticalCuts.length; j++) {
            int horizontalDistance = 0;
            if (j == 0) horizontalDistance = verticalCuts[j];
            else if (j == verticalCuts.length) horizontalDistance = w - verticalCuts[j - 1];
            else {
                horizontalDistance = verticalCuts[j] - verticalCuts[j - 1];
            }
            maxH = Math.max(maxH, horizontalDistance);
        }
        BigInteger bigMaxH = new BigInteger(Integer.toString(maxH));
        BigInteger bigMaxV = new BigInteger(Integer.toString(maxV));
        BigInteger mul = bigMaxH.multiply(bigMaxV);
        BigInteger mod = new BigInteger("1000000007");
        BigInteger result = mul.mod(mod);

        return result.intValue();
    }
}
