package com.blisskid.leetcode;

import java.util.HashMap;

public class SumNum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i =0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int otherIndex = (int)map.get(target - nums[i]);
                if (otherIndex != i) {
                    res[0] = i;
                    res[1] = otherIndex;
                    break;
                }

            }
        }
        return res;
    }


    public static void main(String[] args) {
        // write your code here
        SumNum sn = new SumNum();
        sn.twoSum(new int[]{3,2,4}, 6);
    }
}
