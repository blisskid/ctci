package com.blisskid.leetcode.sort;
import java.util.*;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example 1:
 *
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 *
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class S0324M {

    public static void main(String[] args) {
        S0324M s = new S0324M();
        int[] nums1 = new int[]{18,21,33,62,8,21,10,22,21,4,6,7,14,19,21,300,32,73,43,21,3};
        int[] nums2 = new int[]{4, 5, 5, 6};
        int[] nums3 = new int[]{4,5,5,5,5,6,6,6};
        int[] nums = new int[]{2,1,1,2,1,3,3,3,1,3,1,3,2};
        int[] nums4 = new int[]{1};

        s.wiggleSort(nums);
//        System.out.println(s.quickSelect(nums1, 0, nums1.length - 1, nums1.length / 2));
//        System.out.println(Arrays.toString(nums1));
    }

    private void swap(int index1, int index2, int[] nums) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    //select top k from nums
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end || k > nums.length - 1) {
            return -1;
        }
        //let the first be pivot
        swap(start, end, nums);
        int i = start, j = start;
        while (j < end) {
            if (nums[j] >= nums[end]) {
                j++;
            } else {
                swap(i, j, nums);
                i++;
                j++;
            }
        }
        swap(i, j, nums);
        //now left of i is less to nums[i], right of i is more or equal than nums[i]
        //compare i and k
        if (i < k) {
            //top k
            quickSelect(nums, i + 1, end, k);
        } else if (i > k) {
            quickSelect(nums, start, i - 1, k);
        }

        return nums[k];
    }

    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));
        //1: find the median using quikeselect
        int mediem = quickSelect(nums, 0, nums.length - 1, nums.length / 2);

        System.out.println(Arrays.toString(nums));
        //2: use 3 ways partition to rearrange nums
        int[] temp = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == mediem) {
                count++;
            }
        }

        int index = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            if (nums[i] != mediem) {
                temp[index++] = nums[i];
            }
        }

        for (int i = 0; i < count; i++) {
            temp[index++] = mediem;
        }

        for (int i = nums.length / 2 + 1; i < nums.length; i++) {
            if (nums[i] != mediem) {
                temp[index++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(temp));

        int flag = nums.length % 2 == 0 ? 1 : 0;

        for (int i = nums.length / 2 - flag; i >= 0 ; i--) {
            nums[(nums.length / 2 - flag - i) * 2] = temp[i];
        }
        for (int i = nums.length - 1; i > nums.length / 2 - flag; i--) {
            nums[(nums.length - 1 - i) * 2 + 1] = temp[i];
        }
        //nums[nums.length - 1] = temp[nums.length / 2];
        System.out.println(Arrays.toString(nums));
    }

    public void wiggleSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                //even
                if (nums[i] > nums[i + 1]) {
                    //swap i and i + 1
                    swap(i, i + 1, nums);
                } else if (nums[i] == nums[i + 1]) {
                    //find index which nums[index] < nums[i], and swap i and index
                    Boolean changed = false;
                    for (int j = i + 2; j < nums.length; j++) {
                        if (nums[j] < nums[i]) {
                            swap(i, j, nums);
                            changed = true;
                            break;
                        } else if (nums[j] > nums[i]) {
                            swap(i + 1, j, nums);
                            changed = true;
                            break;
                        }
                    }
                    if (!changed) {
                        //from i, i + 1, i + 2 ... nums.length - 1, all the numbers are the same
                        //swap i and a even number which is not equal to nums[i]
                        for (int j = i - 2; j >= 0; j = j - 2) {
                            if (!(nums[i] == nums[j])) {
                                swap(i, j, nums);
                                //if nums[j] > nums[j - 1], swap them
                                if (j > 0 && nums[j] > nums[j - 1]) {
                                    swap(j, j - 1, nums);
                                }
                                //if nums[i]
                                break;
                            }
                        }
                    }

                }
            } else {
                //odd
                if (nums[i] < nums[i + 1]) {
                    //swap i and i + 1
                    swap(i, i + 1, nums);
                } else if (nums[i] == nums[i + 1]) {
                    if (i == nums.length - 2 && i >= 2) {
                        //swap i and a odd number which is not equal to nums[i]
                        for (int j = 1; j < i - 1; j = j + 2) {
                            if (!(nums[i] == nums[j])) {
                                swap(i, j, nums);
                                //if nums[j] < nums[j - 1], swap them
                                if (nums[j] < nums[j - 1]) {
                                    swap(j, j - 1, nums);
                                }
                                break;
                            }
                        }
                    }
                    //find index which nums[index] > nums[i + 1], and swap i and index
                    for (int j = i + 2; j < nums.length; j++) {
                        if (nums[j] > nums[i]) {
                            swap(j, i, nums);
                            break;
                        } else if (nums[j] < nums[i]) {
                            swap(j, i + 1, nums);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public void wiggleSort1(int[] nums) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp);
        int midIndex = (temp.length - 1) / 2;
        int index = 0;
        for (int i = 0, j = midIndex + 1; i <= midIndex && j < temp.length; i++, j++) {
            nums[index++] = temp[i];
            nums[index++] = temp[j];
        }

        if (index == temp.length - 1) {
            nums[index] = temp[midIndex];
        }

        System.out.println(nums);
    }
}
