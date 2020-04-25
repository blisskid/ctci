package com.blisskid.leetcode.heap;

import java.util.*;

public class S0882 {

    class Vertex {
        public boolean visited;
        public int step;
        public int index;

        public Vertex() {
            visited = false;
            step = -1;
            index = 0;
        }
    }

    class Graph {
        private int MAX_VERTS = 3000;
        private Vertex vertexList[];
        private int adjMat[][];
        //current number of vertices
        private int nVerts;
        private Stack<Integer> theStack;
        private Map<String, Integer> theMap;

        public Graph(int nodes) {
            MAX_VERTS = nodes;
            //adjacency matrix;
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            theStack = new Stack();
            //set's content is small node + large node + index of weight
            theMap = new TreeMap<String, Integer>();

            for (int j = 0; j < MAX_VERTS; j++) // set adjacency
                for (int k = 0; k < MAX_VERTS; k++) // matrix to -1
                    adjMat[j][k] = -1;
        }

        public void addEdge(int start, int end, int weight) {
            adjMat[start][end] = weight;
            adjMat[end][start] = weight;
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public Vertex addVertex()    // argument is label
        {
            Vertex vertex = new Vertex();
            vertex.index = nVerts;
            vertexList[nVerts++] = vertex;
            return vertex;
        }

        public void displayVertex(int v) {
            System.out.print(vertexList[v].index);
        }

        public int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[v][j] >= 0) {
                    int tempStep = vertexList[v].step - adjMat[v][j] - 1;
                    if (tempStep > vertexList[j].step) {
                        //路径上的点都需要加到map中
                        for (int i = 0; i < adjMat[v][j]; i++) {
                            theMap.put(nodeTransfer(v, j, i, adjMat[v][j]), vertexList[v].step - i - 1);
                        }
                        vertexList[j].step = tempStep;
                        return j;
                    } else {
                        //只需要加路径上的点
                        int minStep = Math.min(vertexList[v].step, adjMat[v][j]);
                        for (int i = 0; i < minStep; i++) {
                            theMap.put(nodeTransfer(v, j, i, adjMat[v][j]), vertexList[v].step - i - 1);
                        }
                    }
                }
            }
            return -1;
        }

        public void findNodes(int step, int start) {
            vertexList[start].visited = true;
            vertexList[start].step = step;
            StringBuilder sb = new StringBuilder();
            sb.append(start);
            sb.append("-");
            sb.append(start);
            sb.append("-");
            sb.append("0");
            theMap.put(sb.toString(), step);
            //displayVertex(0);
            theStack.push(start);
            while (!theStack.isEmpty()) {
                int peek = theStack.peek();

                int v = getAdjUnvisitedVertex(peek);
                if (v == -1) {
                    theStack.pop();
                } else {
                    vertexList[v].visited = true;
                    theMap.put(nodeTransfer(v, v, 0, 0), vertexList[v].step);
                    //displayVertex(v);
                    theStack.push(v);
                }
            }

            for (int j = 0; j < nVerts; j++) {
                vertexList[j].visited = false;
            }
        }

        private String nodeTransfer(int node1, int node2, int index, int weight) {
            StringBuilder sb = new StringBuilder();
            if (node1 > node2) {
                sb.append(node2);
                sb.append("-");
                sb.append(node1);
                sb.append("-");
                sb.append(weight - 1 - index);
            } else {
                sb.append(node1);
                sb.append("-");
                sb.append(node2);
                sb.append("-");
                sb.append(index);
            }

            return sb.toString();
        }

    }

    public static void main(String[] args) {
//        int[][] edges = {{0, 2, 3}, {0, 4, 4}, {2, 3, 8}, {1, 3, 5}, {0, 3, 9}, {3, 4, 6}, {0, 1, 5}, {2, 4, 6}, {1, 2, 3}, {1, 4, 1}};
//        int M = 8, N = 5;
//        int[][] edges = {{0,1,4},{1,2,6},{0,2,8},{1,3,1}};
//        int M = 10, N = 4;
        int[][] edges = {{0,11,21},{16,19,74},{1,10,99},{9,12,72},{13,17,80},{1,8,78},{9,13,81},{14,19,16},{3,14,61},{13,14,61},{2,18,88},{12,17,44},{9,16,26},{10,14,54},{1,3,49},{6,13,1},{1,18,4},{6,19,18},{12,15,49},{7,17,66},{10,15,52},{18,19,58},{1,11,100},{14,15,8},{6,16,37},{5,9,55},{11,17,79},{6,18,89},{10,19,62},{3,12,66},{9,10,90},{15,16,79},{5,17,58},{1,16,1},{1,13,41},{14,16,7},{15,19,97},{0,9,83},{12,16,34},{5,14,10},{1,12,56},{0,15,35},{4,16,73},{17,18,84},{6,11,60},{8,11,82},{8,9,0},{3,19,23},{7,18,43},{17,19,46},{2,5,82},{3,7,17},{0,18,57},{6,12,54},{4,15,65},{8,13,1},{14,18,4},{12,14,9},{16,18,39},{5,18,60},{6,10,74},{16,17,65},{8,17,10},{7,11,87},{5,7,39},{11,16,82},{2,9,17},{9,18,38},{9,15,67},{4,13,63},{9,17,52},{1,15,93},{6,8,81},{12,18,60},{7,10,68},{3,6,41},{13,19,51},{7,8,63},{13,15,84},{5,11,20},{1,4,46},{10,11,18},{10,17,19},{7,15,3},{9,19,12},{11,15,76},{3,4,33},{3,8,30},{11,13,14},{0,3,58},{12,19,41},{2,12,5},{11,12,38},{13,18,81},{5,12,76},{9,11,98},{4,9,86},{14,17,50},{15,17,80},{6,17,79}};
        int M = 80, N = 20;
        System.out.println(new S0882().reachableNodes(edges, M, N));
    }

    public int reachableNodes(int[][] edges, int M, int N) {
        if (edges.length == 0) {
            return 0;
        }
        Graph graph = new Graph(N);
        for (int i = 0; i < N; i++) {
            Vertex v = graph.addVertex();
        }
        //create the graph
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
        }
        graph.findNodes(M, 0);

        return graph.theMap.size();
    }

}
