package com.blisskid.leetcode.dac;

public class S0053E {

    public static void main(String[] args) {
        S0053E s = new S0053E();
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(s.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int size = nums.length;
        if (size == 1) return nums[0];
        int[] result = new int[size];
        int end = 1;
        int start = 0;
        result[0] = nums[0];
        for (int i = 1; i < size; i++) {
            int partsum = 0;
            for (int j = end; j <= i; j++) {
                partsum += nums[j];
            }
            if (partsum >= 0 && result[i - 1] >= 0) {
                result[i] = result[i - 1] + partsum;
                end = i + 1;
            } else if (result[i - 1] >= 0 && partsum < 0) {
                result[i] = result[i - 1];
            } else if (result[i - 1] < 0 && partsum >= 0) {
                result[i] = partsum;
                start = end;
                end = i + 1;
            } else {
                //result[i - 1] < 0 && partsum < 0
            }

        }
        return result[size - 1];
    }

    private class Obj {
        int start;
        int end;
        int sum;

        Obj(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    public int maxSubArray1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        Obj re = cal(nums, start, end);
        return re.sum;
    }

    private Obj cal(int[] nums, int start, int end) {
        if (end == start) {
            return new Obj(start, end, nums[start]);
        }

        int mid = (end + start) / 2;
        Obj leftObj = cal(nums, start, mid);
        Obj rightObj = cal(nums, mid + 1, end);
        if (leftObj.end == mid && rightObj.start == mid + 1 && leftObj.sum > 0 && rightObj.sum > 0) {
            return new Obj(leftObj.start, rightObj.end, leftObj.sum + rightObj.sum);
        } else {
            return leftObj.sum > rightObj.sum ? leftObj : rightObj;
        }
    }
}
