package com.blisskid.leetcode;

public class IdealPermutation {

    public boolean isIdealPermutation(int[] A) {
        //if (A == null) return false;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 2; j < A.length; j++) {
                if (A[i] > A[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
