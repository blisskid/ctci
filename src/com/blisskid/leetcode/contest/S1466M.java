package com.blisskid.leetcode.contest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class S1466M {

    public static void main(String[] args) {
        int n = 6;
        int[][] con = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
        //System.out.println(new S1466M().minReorder(n, con));
        Graph graph = new S1466M().new Graph(6);
        graph.addEdge(0, 5);
        graph.addEdge(0, 3);
        graph.addEdge(5, 1);
        graph.addEdge(5, 4);
        graph.addEdge(1, 2);
        graph.dfs();
        System.out.println();
        graph.clear();
        graph.bfs();
    }

    private class Graph {//nodes
        int nodeNum;
        int index = 0;
        int[] nodes;
        //edges
        int[][] edges;
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        Graph(int nodeNum) {
            this.nodeNum = nodeNum;
            this.nodes = new int[nodeNum];
            this.edges = new int[nodeNum][nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                nodes[i] = 0;
                for (int j = 0; j < nodeNum; j++) {
                    edges[i][j] = 0;
                }
            }
        }

        public void addNode(int node) {
            //nodes[index++] = node;
        }

        public void addEdge(int start, int end) {
            edges[start][end] = 1;
            //edges[end][start] = 1;
        }

        private int getToNode(int node) {
            for (int i = 0; i < nodeNum ; i++) {
                if (edges[node][i] == 1 && nodes[i] != 1) {
                    return i;
                }
            }
            return -1;
        }

        public void bfs() {
            nodes[0] = 1;
            System.out.print("0");
            queue.add(0);
            while (!queue.isEmpty()) {
                int tempNode = queue.remove();
                int adjNode;
                while ((adjNode = getToNode(tempNode)) != -1) {
                    System.out.print(adjNode);
                    nodes[adjNode] = 1;
                    queue.add(adjNode);
                }
            }
        }

        public void clear() {
            for (int i = 0; i < nodeNum; i++) {
                nodes[i] = 0;
            }
        }

        public void dfs() {
            nodes[0] = 1;
            System.out.print("0");
            stack.add(0);
            while (!stack.isEmpty()) {
                int adjNode = getToNode(stack.peek());
                if (adjNode != -1) {
                    System.out.print(adjNode);
                    nodes[adjNode] = 1;
                    stack.add(adjNode);
                } else {
                    stack.pop();
                }
            }
        }

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
