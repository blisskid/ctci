package com.blisskid.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private class Node {
        private Integer data;
        private List<Node> children;

        Node(Integer data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(Node node) {
            this.children.add(node);
        }

        public boolean hasChild() {
            return this.children.size() == 0;
        }
    }

    private Node root = null;

    public Graph createGraph(int[][] richer, int[] quiet) {
        Graph graph = new Graph();
        Queue<Node> queue = new LinkedList<>();

        //queue.add();

        for (int i = 0; i < richer.length; i++) {
            if (richer[i][1] == 0) {
                if (graph.root == null) {
                    graph.root = new Node(richer[i][1]);
                }
                graph.root.addChild(new Node(richer[i][0]));
            }
        }

        /*
        for (int j = 1; j < quiet.length; j++) {
            for (int i = 0; i < richer.length; i++) {
                if (richer[i][1] == j) {
                    if (graph.root == null) {
                        graph.root = new Node(richer[i][1]);
                    }
                    graph.root.addChild(new Node(richer[i][0]));
                }
            }
        }
    */


        return graph;
    }

    public void traverse(Graph graph) {

    }
}
