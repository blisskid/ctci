package com.blisskid.leetcode.tree;

import java.util.*;

public class S0295H1 {
    /**
     * initialize your data structure here.
     */

    public static void main(String[] args) {
        S0295H s = new S0295H();
        s.addNum(6);
        s.addNum(10);
        s.addNum(2);
        s.addNum(6);
        s.addNum(5);
        s.addNum(0);
        s.addNum(6);
        s.addNum(3);
        s.addNum(1);
        s.addNum(0);
        s.addNum(0);
        System.out.println(s.findMedian());
    }


    /** initialize your data structure here. */

    List<Integer> list = null;

    public S0295H1() {
        list = new ArrayList<Integer>();
    }

    public void addNum(int num) {
        int size = list.size();
        if (size == 0) {
            list.add(num);
        } else {
            int index = findIndex(0, size - 1, num);
            list.add(index, num);
        }
    }

    private int findIndex(int start, int end, int num) {
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (list.get(mid) < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (list.get(start) < num) {
            return start + 1;
        } else {
            return start;
        }

    }

    public double findMedian() {
        int size = list.size();
        if (size == 0) return 0;
        if (size % 2 == 0) {
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0;
        } else {
            return list.get(size / 2);
        }
    }
}
