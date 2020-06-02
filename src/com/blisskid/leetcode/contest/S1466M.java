package com.blisskid.leetcode.contest;

public class S1466M {

    public static void main(String[] args) {
        int n = 6;
        int[][] con = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println(new S1466M().minReorder(n, con));
    }

    public int minReorder(int n, int[][] connections) {
        //set includes the cities can reach 0;
        int result = 0;
        for (int i = 1; i < n; i++) {
            if (!canReachZero(i, connections)) {
                result++;
            }
        }
        return result;
    }

    private Boolean canReachZero(int city, int[][] connections) {
        if (city == 0) return true;
        for (int i = 0; i < connections.length; i++) {
            if (city == connections[i][0]) {
                if (canReachZero(connections[i][1], connections)) {
                    return true;
                }
            }

        }
        return false;
    }
}
