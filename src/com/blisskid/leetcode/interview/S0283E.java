package com.blisskid.leetcode.interview;

public class S0283E {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,0,3,12};
        new S0283E().moveZeroes(arr);
    }

    public void moveZeroes(int[] nums) {
        //from end to start, use two pointers
        int pointerZero = nums.length - 1;
        while (nums[pointerZero] == 0 && --pointerZero >= 0);
        int pointerNonZero = pointerZero;

        while (pointerZero >= 0 && pointerNonZero >= 0) {
            //while (nums[pointerNonZero] == 0 && --pointerNonZero >= 0);
            //pointerZero = pointerNonZero;
            while (nums[pointerZero] != 0 && --pointerZero >= 0);
            //pointerZero point to zero
            if (pointerZero >= 0 && pointerNonZero >= 0) {
                //swap zero to the pointerNonZero index and move numbers between two pointers
                int temp = nums[pointerZero];
                for (int i = pointerZero; i < pointerNonZero; i++) {
                    nums[i] = nums[i + 1];
                }
                nums[pointerNonZero] = temp;
                pointerNonZero--;
            }
        }
    }
}
