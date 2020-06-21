package com.blisskid.datastructure;

public class Graph {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('0');   //0
        graph.addVertex('1');   //1
        graph.addVertex('2');
        graph.addVertex('3');
        graph.addVertex('4');
        graph.addVertex('5');

        graph.addEdge(0, 5);
        graph.addEdge(0, 3);
        graph.addEdge(5, 1);
        graph.addEdge(5, 4);
        graph.addEdge(1, 2);
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    //current number of vertices
    private int nVerts;
    private StackX theStack;
    private Queue theQueue;

    public Graph() {
        //adjacency matrix;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        theStack = new StackX();
        theQueue = new Queue();
        for(int j=0; j<MAX_VERTS; j++) // set adjacency
            for(int k=0; k<MAX_VERTS; k++) // matrix to 0
                adjMat[j][k] = 0;
    }


    public void addVertex(char lab)    // argument is label
    {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end)
    {
        adjMat[start][end] = 1; adjMat[end][start] = 1;
    }
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void dfs() {
        vertexList[0].visited = true;
        displayVertex(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1) {
                theStack.pop();
            } else {
                vertexList[v].visited = true;
                displayVertex(v);
                theStack.push(v);
            }
        }

        for (int j = 0; j < nVerts; j++) {
            vertexList[j].visited = false;
        }
    }

    public void bfs() {
        vertexList[0].visited = true;
        displayVertex(0);
        theQueue.insert(0);
        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].visited = true;
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }

        for (int j = 0; j < nVerts; j++) {
            vertexList[j].visited = false;
        }
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
