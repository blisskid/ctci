package com.blisskid.leetcode.sort;

public class S0912M {
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        new S0912M().sortArray(nums);
    }

    public int[] sortArray(int[] nums) {
        return merge(nums, 0, nums.length - 1);

    }

    private int[] merge(int[] a, int start, int end) {
        int size = end - start + 1;
        int[] result = new int[size];
        if (size == 1) {
            result[0] = a[start];
        } else if (size == 2) {
            if (a[start] < a[end]) {
                result[0] = a[start];
                result[1] = a[end];
            } else {
                result[0] = a[end];
                result[1] = a[start];
            }
        } else {
            int mid = (start + end) / 2;
            int[] left = merge(a, start, mid);
            int[] right = merge(a, mid + 1, end);
            //merge left and right

            int i = 0, j = 0, k = 0;
            while (k < size) {
                while (i < left.length && left[i] < right[j]) {
                    result[k++] = left[i++];
                }
                if (i == left.length) {
                    while (j < right.length) result[k++] = right[j++];
                    break;
                }
                while (j < right.length && left[i] >= right[j]) {
                    result[k++] = right[j++];
                }
                if (j == right.length) {
                    while (i < left.length)  result[k++] = left[i++];
                    break;
                }
            }
        }
        return result;
    }
}
