package com.blisskid.leetcode;

public class Solution867 {
    public static void main(String[] args) {
        int[][] A = {{1,2,3},{4,5,6}};
        int[][] transpose = new Solution867().transpose(A);
    }

    public int[][] transpose(int[][] A) {
        int rowSize = A.length;
        int colSize = A[0].length;
        int[][] result = new int[colSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                result[j][i] = A[i][j];
            }
        }
        return result;
    }
}
