package com.blisskid.leetcode.sort;

public class S0215M {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(new S0215M().findKthLargest(nums, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        return sort(nums, 0, nums.length - 1, k);
    }

    private int sort(int[] nums, int start, int end, int k) {
        int size = end - start + 1;
        int[] temp = nums.clone();
        if (size == 1) return nums[start];
        int seed = nums[start];
        int left = 0, right = 0;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < seed) {
                temp[start + left] = nums[i];
                left++;
            } else {
                temp[end - right] = nums[i];
                right++;
            }
        }
        temp[left + start] = seed;
        int order = right + 1;
        if (order == k) {
            return seed;
        } else if (order < k) {
            return sort(temp, start, start + left - 1, k - order);
        } else {
            return sort(temp, start + left + 1, end, k);
        }
    }
}
