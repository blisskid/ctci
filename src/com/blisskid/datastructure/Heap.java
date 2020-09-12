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

        //h.print(0);
        int[] res = h.sort();
        for (int i : res) {
            System.out.println(i);
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

    public int length() {
        return index;
    }

    public int[] sort() {
        int[] res = new int[this.length()];
        for (int i = 0; i < 10; i++) {
            res[i] = this.remove();
        }
        return res;
    }

    //remove top, rejust heap
    public int remove() {
        int res = arr[0];
        swap(0, index - 1);
        index--;
        adjFromTop(0, arr[0]);
        return res;
    }

    public int getLast() {
        return arr[index - 1];
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
        adjFromBottom(index, val);
        index++;
    }

    //ajust arr, make the order correct
    private void adjFromBottom(int i, int val) {
        if (i <= 0) return;
        //check if this val is smaller than parent or brother
        int pi = (i - 1) / 2;
        int parent = this.arr[pi];
        if (val < parent) {
            swap(pi, i);
            adjFromBottom(pi, val);
        }
    }

    private void adjFromTop(int i, int val) {
        int lc = 2 * i + 1;
        int rc = 2 * i + 2;
        if (rc < this.length()) {
            if (arr[lc] < arr[rc]) {
                if (arr[lc] < val) {
                    swap(lc, i);
                    adjFromTop(lc, val);
                }
            } else {
                if (arr[rc] < val) {
                    swap(rc, i);
                    adjFromTop(rc, val);
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
