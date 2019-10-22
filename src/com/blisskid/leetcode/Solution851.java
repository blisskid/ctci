package com.blisskid.leetcode;

import com.blisskid.datastructure.LeetGraph;

import java.util.Arrays;

public class Solution851 {

    public static void main(String[] args) {
        Solution851 solution = new Solution851();
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        System.out.println(Arrays.toString(solution.loudAndRich(richer, quiet)));
        System.out.println(Arrays.toString(solution.loudAndRich1(richer, quiet)));
    }

    private int[] resultArr = null;
    private LeetGraph graph = null;

    public int[] loudAndRich1(int[][] richer, int[] quiet) {
        //listArr to store richer than the key's person
        int size = quiet.length;

        resultArr = new int[size];
        for (int i = 0; i < size; i++) {
            resultArr[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            findLoudest(richer, quiet, i);
        }

        return resultArr;
    }

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //create graph by richer and quiet
        graph = new LeetGraph();

        resultArr = new int[quiet.length];

        for (int i = 0; i < quiet.length; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < richer.length; i++) {
            graph.addEdge(richer[i][1], richer[i][0]);
        }

//        graph.dfs();
//        System.out.println();

        for (int i = 0; i < quiet.length; i++) {
            resultArr[i] = graph.findLoudest(i, quiet);
            System.out.println();
        }

        return resultArr;

    }

    private int findLoudest(int[][] richer, int[] quiet, int index) {
        if (resultArr[index] != -1) return resultArr[index];
        int result = quiet[index];
        int person = index;
        for (int i = 0; i < richer.length; i++) {
            //find richer than index
            if (index == richer[i][1]) {
                //get the result
                int loudPerson = findLoudest(richer, quiet, richer[i][0]);
                if (result > quiet[loudPerson]) {
                    result = quiet[loudPerson];
                    person = loudPerson;
                }
            }
        }
        resultArr[index] = person;
        return person;
    }
}
