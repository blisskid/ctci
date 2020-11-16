package com.blisskid.fb;
import java.util.*;

public class S0015M {

    public static void main(String[] args) {
        S0015M s = new S0015M();
        int[] nums = new int[]{0,0,0};
        System.out.println(s.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList();
        for(int i=0;i<nums.length-2;i++){
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int start=i+1;
            int end=nums.length-1;
            while(start<end){
                int sum=nums[i]+nums[start]+nums[end];
                if(sum<0){
                    start++;
                    while(start>i+1&&start<end&&nums[start]==nums[start-1]) {
                        start++;
                    }
                }else if(sum>0){
                    end--;
                    while(end<nums.length-1&&start<end&&nums[end]==nums[end+1]) {
                        end--;
                    }
                }else{
                    List<Integer> tempList = new ArrayList();
                    tempList.add(nums[i]);
                    tempList.add(nums[start]);
                    tempList.add(nums[end]);
                    result.add(tempList);
                    start++;
                    end--;
                    while(start>i+1&&start<end&&nums[start]==nums[start-1]) {
                        start++;
                    }
                    while(end<nums.length-1&&start<end&&nums[end]==nums[end+1]) {
                        end--;
                    }
                }
            }
        }
        return result;
    }
}
