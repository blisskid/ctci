package com.blisskid.leetcode;

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

        Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

        You may assume nums1 and nums2 cannot be both empty.

        Example 1:

        nums1 = [1, 3]
        nums2 = [2]

        The median is 2.0
        Example 2:

        nums1 = [1, 2]
        nums2 = [3, 4]

        The median is (2 + 3)/2 = 2.5
        */

public class Median {

    public static void main(String[] args) {
        Median median = new Median();
    }

    private void change(AbPojo abPojo) {
        abPojo.setA(10);
    }

    private void change(int[] arr) {
        arr[0] = 100;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m == 0 && n != 0) {
            return n % 2 == 0 ? ((nums2[n / 2 - 1] + nums2[n / 2]) / 2.0) : nums2[n / 2];
        }
        if (n == 0 && m != 0) {
            return m % 2 == 0 ? ((nums1[m / 2 - 1] + nums1[m / 2]) / 2.0) : nums1[m / 2];
        }
        int start = 0, end = m;
        int partition1 = (start + end) / 2;
        int partition2 = (m + n) / 2 - partition1;
        while (start <= end) {
            int left1 = 0, right1 = 0, left2 = 0, right2 = 0;
            if (partition1 <= 0) {
                left1 = Integer.MIN_VALUE;
                right1 = nums1[0];
            } else if (partition1 >= m) {
                left1 = nums1[m - 1];
                right1 = Integer.MAX_VALUE;
            } else {
                left1 = nums1[partition1 - 1];
                right1 = nums1[partition1];
            }

            if (partition2 <= 0) {
                left2 = Integer.MIN_VALUE;
                right2 = nums2[0];
            } else if (partition2 >= n) {
                left2 = nums2[n - 1];
                right2 = Integer.MAX_VALUE;
            } else {
                left2 = nums2[partition2 - 1];
                right2 = nums2[partition2];
            }

            //compute the two medians
            if (left1 > right2) {
                //move to left
                end--;
                partition1 = (start + end) / 2;
                partition2 = (m + n) / 2 - partition1;
            } else if (left2 > right1) {
                //move to right
                start++;
                partition1 = (start + end) / 2;
                partition2 = (m + n) / 2 - partition1;
            } else if (left1 <= right2 && left2 <= right1) {
                int leftMax = Math.max(left1, left2);
                int rightMin = Math.min(right1, right2);
                //even num of int
                if ((m + n) % 2 == 0) {
                    return (leftMax + rightMin) / 2.0;
                } else {
                    return rightMin;
                }
            }
        }
        return 0.0;
    }
}

class AbPojo {
    private int a = 0;
    private int b = 0;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
