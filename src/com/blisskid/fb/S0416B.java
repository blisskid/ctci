package com.blisskid.fb;
import java.util.*;

class S0416B {
    public static void main(String[] args) {
//        int[] nums=new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums=new int[]{14,9,8,4,3,2};
        System.out.println(new S0416B().canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        List<Integer> l=new ArrayList();
        int sum=0;
        for(int i=0;i<nums.length;i++){
            l.add(nums[i]);
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }else{
            return f(l,sum/2);
        }
    }

    private boolean f(List<Integer> l,int sum){
        if(l.size()==1){
            return l.get(0)==sum;
        }else{
            boolean flag=false;
            for(int i=0;i<l.size();i++){
                Integer ele=l.get(i);
                if(ele<sum){
                    List<Integer> temp=new ArrayList();
                    temp.addAll(l);
                    temp.remove(i);
                    flag=f(temp,sum-ele);
                }else if(ele==sum){
                    return true;
                }
            }
            return flag;
        }
    }
}
