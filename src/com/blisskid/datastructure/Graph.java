package com.blisskid.datastructure;

public class Graph {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');   //0
        graph.addVertex('B');   //1
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge(0, 1); //AB
        graph.addEdge(1, 2); //BC
        graph.addEdge(0, 3); //AD
        graph.addEdge(3, 4); //DE
    }

    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    //current number of vertices
    private int nVerts;
    private StackX theStack;

    public Graph() {
        //adjacency matrix;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
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

        }
    }
}
