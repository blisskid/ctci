package com.blisskid.leetcode.heap;

class MinHeap {

    private Node[] arr;
    private int index = 0;

    MinHeap(int size) {
        arr = new Node[size];
    }

    public int getSize() {
        return index;
    }

    //insert data
    public void insert(Node node) {
        if (index >= arr.length) {
            return;
        }
        arr[index++] = node;
        upHeap(index - 1);
    }

    //remove top and return topNode data
    public Node remove() {
        Node top = arr[0];
        arr[0] = arr[index - 1];
        arr[index - 1] = null;
        index--;
        downHeap(0);
        return top;
    }

    public void upHeap(int index) {
        if (index == 0) return;
        Node parent = arr[(index - 1) / 2];
        if (parent.data > arr[index].data) {
            //swap parent and child
            arr[(index - 1) / 2] = arr[index];
            arr[index] = parent;
            upHeap((index - 1) / 2);
        }

    }

    public void downHeap(int index) {
        //if no child ,return;
        if (index * 2 + 1 > this.getSize() - 1) return;
        Node leftChild = arr[index * 2 + 1];
        if (leftChild != null && leftChild.data < arr[index].data) {
            arr[index * 2 + 1] = arr[index];
            arr[index] = leftChild;
            downHeap(index * 2 + 1);
        }

        if (index * 2 + 2 > this.getSize() - 1) return;
        Node rightChild = arr[index * 2 + 2];
        if (rightChild != null && rightChild.data < arr[index].data) {
            arr[index * 2 + 2] = arr[index];
            arr[index] = rightChild;
            downHeap(index * 2 + 2);
        }
    }

}