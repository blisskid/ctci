package com.blisskid.leetcode.bit;
import com.blisskid.leetcode.greedy.S0763M;

import java.util.*;
/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 */

public class S0136E {

    public static void main(String[] args) {
        System.out.println(new S0136E().findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    public int findMaximumXOR(int[] nums) {
        int maxNum = nums[0];
        for(int num : nums) maxNum = Math.max(maxNum, num);
        // length of max number in a binary representation
        int L = (Integer.toBinaryString(maxNum)).length();

        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for(int i = L - 1; i > -1; --i) {
            // go to the next bit by the left shift
            maxXor <<= 1;
            // set 1 in the smallest bit
            currXor = maxXor | 1;
            prefixes.clear();
            // compute all possible prefixes
            // of length (L - i) in binary representation
            for(int num: nums) prefixes.add(num >> i);
            // Update maxXor, if two of these prefixes could result in currXor.
            // Check if p1^p2 == currXor, i.e. p1 == currXor^p2.
            for(int p: prefixes) {
                if (prefixes.contains(currXor^p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }


}
