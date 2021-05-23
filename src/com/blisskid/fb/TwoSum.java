package com.blisskid.fb;

import com.blisskid.leetcode.other.TreeNode;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum t=new TwoSum();
        System.out.println(t.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threeSum(nums,0,0);
    }

    private List<List<Integer>> threeSum(int[] nums,int start,int target){
        List<List<Integer>> result=new ArrayList();
        for(int i=start;i<nums.length;i++){
            List<List<Integer>> sum=twoSum(nums,i+1,target-i);
            for(List<Integer> l:sum){
                l.add(i);
            }
            result.addAll(sum);
            while(i<nums.length-1&&nums[i]==nums[i+1])  i++;
        }
        return result;
    }

    private List<List<Integer>> twoSum(int[] arr,int start,int target){
        List<List<Integer>> result=new ArrayList();
        int end=arr.length-1;
        //Arrays.sort(arr);
        while(start<end){
            while(start<end&&arr[start]==arr[start+1]) start++;
            //arr[start] is the last not equal one
            while(start<end&&arr[end]==arr[end-1]) end--;
            if(start==end)  break;
            //arr[end] is the last not equal one
            int sum=arr[start]+arr[end];
            if(sum==target){
                List<Integer> temp=new ArrayList();
                temp.add(arr[start]);
                temp.add(arr[end]);
                result.add(temp);
                start++;
                end--;
            }else if(sum<target){
                start++;
                //while(start<end&&arr[start]==arr[start+1]) start++;
                //if(start==end)  break;
            }else{
                end--;
                //while(start<end&&arr[end]==arr[end-1]) end--;
                //if(start==end)  break;
            }
        }
        return result;
    }
}
