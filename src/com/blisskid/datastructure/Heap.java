package com.blisskid.datastructure;

public class Heap {

    public static void main(String[] args) {
        Heap h = new Heap(10);
        h.add(-1);
        h.add(5);
        h.add(3);
        h.add(9);
        h.add(10);
        h.add(2);
        h.add(12);
        h.add(21);
        h.add(-5);
        h.add(-8);

        h.print(0);
    }

    private class Node {
        int data;
        Node left;
        Node right;

        public Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    private int[] arr;
    private int index;

    public Heap(int size) {
        arr = new int[size];
        index = 0;
    }

    public int top() {
        return 0;
    }

    public void remove() {

    }

    public void print(int index) {
        if (index >= this.arr.length)
            return;
        System.out.println(arr[index]);
        print(2 * index + 1);
        print(2 * index + 2);
    }

    public void add(int val) {
        arr[index] = val;
        adj(index, val);
        index++;
    }

    //ajust arr, make the order correct
    private void adj(int i, int val) {
        if (i <= 0) return;
        //check if this val is smaller than parent or brother
        int pi = (i - 1) / 2;
        int parent = this.arr[pi];
        if (val < parent) {
            swap(pi, i);
            adj(pi, val);
        } else {
            if (i % 2 == 0) {
                //has left brother
                int li = i - 1;
                int brother = this.arr[li];
                if (val < brother) {
                    swap(li, i);
                }
            }
        }
    }

    //swap two element is arr
    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
