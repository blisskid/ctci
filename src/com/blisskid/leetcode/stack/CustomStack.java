package com.blisskid.leetcode.stack;

class CustomStack {

    private int[] arr;
    private int index;
    private int maxSize;

    public static void main(String[] args) {
        CustomStack c = new CustomStack(3);
        c.push(1);
        c.push(2);
        c.pop();
        c.push(2);
        c.push(3);
        c.push(4);
        c.increment(5, 100);
        c.increment(2, 100);
        c.pop();
        c.pop();
        c.pop();
        c.pop();
    }

    public CustomStack(int maxSize) {
        arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = -1;
        }
        index = 0;
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (index < maxSize) {
            arr[index++] = x;
        }
    }

    public int pop() {
        if (index > 0) {
            index--;
            int result = arr[index];
            arr[index] = -1;
            return result;
        } else {
            return -1;
        }
    }

    public void increment(int k, int val) {
        for (int i = 0; i < k && i < index; i++) {
            arr[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
