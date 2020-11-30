package com.blisskid.fb;
import java.util.*;

public class S0416M {
    public static void main(String[] args) {
//        int[] nums=new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums=new int[]{1,2,3};
        System.out.println(new S0416M().canPartition(nums));
    }

    LinkedList<Integer> track1=new LinkedList();
    LinkedList<Integer> track2=new LinkedList();
    LinkedList<Integer> choices=new LinkedList();

    boolean res=false;
    int len=0;
    public boolean canPartition(int[] nums) {
        if(nums.length==0)
            return false;
        for(int i=0;i<nums.length;i++){
            choices.add(nums[i]);
        }
        f();
        return res;
    }

    private void f(){
        if(choices.isEmpty()){
            System.out.println("track1:"+track1);
            System.out.println("track2:"+track2);
            if(check()==0){
                res=true;
            }
            //track1.clear();
            //track2.clear();
            return;
        }
        Integer temp=choices.removeFirst();
        if(track2.size()>0||track1.size()>0){
            if(check()<0){
                track1.add(temp);
                f();
                track1.removeLast();
            }else if(check()>0){
                track2.add(temp);
                f();
                track2.removeLast();
            }
        }else{
            track1.add(temp);
            f();
            track1.removeLast();
            track2.add(temp);
            f();
            track2.removeLast();
        }

        /*
        track1.add(temp);
        f();
        track2.add(track1.removeLast());
        f();
        track2.removeLast();
        */
        choices.addFirst(temp);
    }

    private int check(){
        int sum1=0,sum2=0;
        for(Integer i:track1){
            sum1+=i;
        }
        for(Integer i:track2){
            sum2+=i;
        }
        return sum1-sum2;
    }
}
