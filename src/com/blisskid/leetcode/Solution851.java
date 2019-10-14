package com.blisskid.leetcode;

public class Solution851 {

    public static void main(String[] args) {
        Solution851 solution = new Solution851();
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        System.out.println(solution.loudAndRich(richer, quiet));
    }

    int[] resultArr = null;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //listArr to store richer than the key's person
        int size = quiet.length;

        resultArr = new int[size];
        for (int i = 0; i < size; i++) {
            resultArr[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            findQuietest(richer, quiet, i);
        }

        return resultArr;
    }


    private int findQuietest(int[][] richer, int[] quiet, int index) {
        if (resultArr[index] != -1) return resultArr[index];
        int result = quiet[index];
        for (int i = 0; i < richer.length; i++) {
            //find richer than index
            if (index == richer[i][1]) {
                //get the result
                int temp = findQuietest(richer, quiet, richer[i][0]);
                if (result > temp) {
                    result = temp;
                }
            }
        }
        resultArr[index] = result;
        return result;
    }
}
