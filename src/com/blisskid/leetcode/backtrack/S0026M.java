package com.blisskid.leetcode.backtrack;
import java.util.*;

public class S0026M {
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3};
        System.out.println(new S0026M().permute(nums));
    }

    private List<List<Integer>> res=new ArrayList();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track=new LinkedList();
        //Queue<Integer> choices=new LinkedList<>();
        //initialize choices
        /*
        for(int i=0;i<nums.length;i++){
            choices.add(nums[i]);
        }
        */
        f(track,nums);
        return res;
    }

    private void f(LinkedList<Integer> track, int[] choices){
        //List<Integer> track=new LinkedList<>(t);
        //List<Integer> choices=new ArrayList<>(c);
        if(track.size()==choices.length){
            res.add(new LinkedList<>(track));
            //track=new ArrayList();
            return;
        }else{
            //List<Integer> tempChoices=new ArrayList(choices);
            //List<Integer> tempTrack=new ArrayList(track);
            for(int i=0;i<choices.length;i++){
                if(!track.contains(choices[i])){
                    track.add(choices[i]);
                    f(track, choices);
                    track.removeLast();
                }
            }
        }
    }
}
