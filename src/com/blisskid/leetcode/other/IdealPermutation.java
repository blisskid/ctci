package com.blisskid.leetcode.other;

public class IdealPermutation {

    public boolean isIdealPermutationA(int[] A) {
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

    public boolean isIdealPermutation(int[] A) {
        //if (A == null) return false;
        for (int i = 0; i < A.length; i++) {

        }
        return true;
    }

}
