package com.blisskid.leetcode.interview;

import java.util.*;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */

public class S0703E {

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4,5,8,2,3,9,11,1,0,-1};
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i : arr) {
            queue.add(i);
        }
        while (queue.size() > 0)
            System.out.println(queue.remove());

        /*
        S0703E kthLargest = new S0703E(3, arr);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
        */
    }

    private PriorityQueue<Integer> heap = new PriorityQueue();
    private int K;

    public S0703E(int k, int[] nums) {
        K = k;
        for (int a : nums) {
            add(a);
        }
    }

    public int add(int val) {
        heap.add(val);
        if (heap.size() > this.K) {
            heap.remove();
        }
        return heap.peek();
    }
}
