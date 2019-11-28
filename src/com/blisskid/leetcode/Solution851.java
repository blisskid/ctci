package com.blisskid.leetcode;

import java.util.Arrays;

public class Solution851 {

    public static void main(String[] args) {
        Solution851 solution = new Solution851();
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        System.out.println(Arrays.toString(solution.loudAndRich(richer, quiet)));
        System.out.println(Arrays.toString(solution.loudAndRich1(richer, quiet)));
    }

    private class LeetVertex {
        public int label;
        public boolean visited;

        public LeetVertex(int lab) {
            label = lab;
            visited = false;
        }
    }

    private class StackX {
        private final int SIZE = 500;
        private int[] st;
        private int top;
        public StackX() {
            st = new int[SIZE];
            top = -1;
        }

        public void push (int j) {
            st[++top] = j;
        }

        public int pop() {
            return st[top--];
        }

        public int peek() {
            return st[top];
        }

        public boolean isEmpty() {
            return (top == -1);
        }
    }


    public class LeetGraph {

        private final int MAX_VERTS = 500;
        private LeetVertex vertexList[];
        private int adjMat[][];
        //current number of vertices
        private int nVerts;
        private StackX theStack;

        public LeetGraph() {
            //adjacency matrix;
            vertexList = new LeetVertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            theStack = new StackX();
            for (int j = 0; j < MAX_VERTS; j++) // set adjacency
                for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
                    adjMat[j][k] = 0;
        }


        public void addVertex(int lab)    // argument is label
        {
            vertexList[nVerts++] = new LeetVertex(lab);
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
            //adjMat[end][start] = 1;
        }

        public int findLoudest(int v, int[] quiet) {
            int min = quiet[v];
            int minIndex = v;
            vertexList[v].visited = true;
            this.theStack.push(v);
            while (!theStack.isEmpty()) {
                int v1 = getAdjUnvisitedVertex(theStack.peek());
                if (v1 == -1) {
                    theStack.pop();
                } else {
                    vertexList[v1].visited = true;
                    if (min > quiet[v1]) {
                        min = quiet[v1];
                        minIndex = v1;
                    }
                    theStack.push(v1);
                }
            }

            for (int j = 0; j < nVerts; j++) {
                vertexList[j].visited = false;
            }

            return minIndex;
        }

        public int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[v][j] == 1 && vertexList[j].visited == false) {
                    return j;
                }
            }
            return -1;
        }
    }

    private int[] resultArr = null;
    private LeetGraph graph = null;


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

        for (int i = 0; i < quiet.length; i++) {
            resultArr[i] = graph.findLoudest(i, quiet);
            System.out.println();
        }

        return resultArr;

    }

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
