package com.blisskid.leetcode.heap;

class Heap {

    private Integer[] arr;
    private int index = 0;

    Heap(int size) {
        arr = new Integer[size];
    }

    public int getSize() {
        return index;
    }

    //insert data
    public void insert(Integer node) {
        if (index >= arr.length) {
            return;
        }
        arr[index++] = node;
        upHeap(index - 1);
    }

    //remove top and return topNode data
    public Integer remove() {
        Integer top = arr[0];
        arr[0] = arr[index - 1];
        arr[index - 1] = null;
        index--;
        downHeap(0);
        return top;
    }

    public void upHeap(int index) {
        if (index == 0) return;
        Integer parent = arr[(index - 1) / 2];
        if (parent < arr[index]) {
            //swap parent and child
            arr[(index - 1) / 2] = arr[index];
            arr[index] = parent;
            upHeap((index - 1) / 2);
        }

    }

    public void downHeap(int index) {
        //if no child ,return;
        if (index * 2 + 1 > this.getSize() - 1) return;
        Integer leftChild = arr[index * 2 + 1];
        if (leftChild != null && leftChild > arr[index]) {
            arr[index * 2 + 1] = arr[index];
            arr[index] = leftChild;
            downHeap(index * 2 + 1);
        }

        if (index * 2 + 2 > this.getSize() - 1) return;
        Integer rightChild = arr[index * 2 + 2];
        if (rightChild != null && rightChild > arr[index]) {
            arr[index * 2 + 2] = arr[index];
            arr[index] = rightChild;
            downHeap(index * 2 + 2);
        }
    }

}
