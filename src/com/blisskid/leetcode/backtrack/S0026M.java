package com.blisskid.leetcode.backtrack;
import java.util.*;

public class S0026M {
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3};
        System.out.println(new S0026M().permute(nums));
    }

    private List<List<Integer>> res=new ArrayList();
    LinkedList<Integer> track=new LinkedList();
    //Set<Integer> track=new HashSet();

    public List<List<Integer>> permute(int[] nums) {

        f(nums);
        return res;
    }

    private void f(int[] choices){
        if(track.size()==choices.length){
            res.add(new LinkedList(track));
            return;
        }else{
            for(int i=0;i<choices.length;i++){
                if(!track.contains(choices[i])){
                    track.add(choices[i]);
                    f(choices);
                    track.remove((Integer)choices[i]);
                }
            }
        }
    }
}
