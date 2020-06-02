package com.blisskid.leetcode.sort;
import java.util.*;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *
 * Return 0 if the array contains less than 2 elements.
 *
 * Example 1:
 *
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 *              (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * Note:
 *
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 */

public class S0164H {

    public static void main(String[] args) {
        //System.out.println(new S0164H().maximumGap(new int[]{100,3,2,1}));
        System.out.println(new S0164H().maximumGap(new int[]{3,6,9,1}));

    }

    public int maximumGap(int[] nums) {
        //calculate the max length of nums
        String[] numsStr = new String[nums.length];
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = new Integer(nums[i]).toString();
            maxLen = Math.max(numsStr[i].length(), maxLen);
        }
        radixSort(numsStr, maxLen);
        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, nums[i] - nums[i - 1]);
        }
        return result;
    }

    private void radixSort(String[] numsStr, int maxLen) {

        //add '0' before the nums whose length is smaller than max length
        for (int i = 0; i < numsStr.length; i++) {
            if (numsStr[i].length() < maxLen) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < maxLen - numsStr[i].length(); j++) {
                    sb.append("0");
                }
                sb.append(numsStr[i]);
                numsStr[i] = sb.toString();
            }
        }

        //for (int i = 0; i < maxLen; i++) {
        for (int i = maxLen - 1; i >= 0; i--) {
            List<String>[] sortList = new ArrayList[10];
            for (int j = 0; j < numsStr.length; j++) {
                int index = numsStr[j].charAt(i) - '0';
                if (null == sortList[index])
                    sortList[index] = new ArrayList<String>();
                sortList[index].add(numsStr[j]);
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (null != sortList[j] && sortList[j].size() >0) {
                    Iterator<String> itr = sortList[j].iterator();
                    while (itr.hasNext()) {
                        numsStr[index++] = itr.next();
                    }
                }
            }
        }
    }
}
