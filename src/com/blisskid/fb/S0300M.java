package com.blisskid.fb;
import java.util.*;

public class S0300M {

    public static void main(String[] args) {
        int[] nums=new int[]{10,9,2,5,3,4};
        System.out.println(new S0300M().lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        int len=nums.length;
        int res=1,f=1;
        //save min value of n
        List<Integer> list=new ArrayList();
        list.add(0);
        list.add(nums[0]);
        for(int i=1;i<len;i++){
            for(int j=list.size()-1;j>0;j--){
                if(nums[i]>list.get(j)){
                    f=j+1;
                    if(j==list.size()-1){
                        list.add(nums[i]);
                    }else{
                        if(nums[i]<list.get(j+1)){
                            list.set(j+1,nums[i]);
                        }
                    }
                    break;
                }else{
                    if(j==1){
                        list.set(1,nums[i]);
                    }
                }
            }
            res=Math.max(f,res);
        }
        return res;
    }
}
