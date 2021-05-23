package com.blisskid.fb;

import java.util.*;

public class MaxEnvelopes {
    public static void main(String[] args) {
        MaxEnvelopes t=new MaxEnvelopes();
        int[][] a=new int[][]{{5,4},{6,4},{6,7},{2,3}};
        System.out.println(t.maxEnvelopes(a));
    }

    public int maxEnvelopes(int[][] envelopes) {
        Map<Integer,TreeSet<Integer>> map=new TreeMap<>();
        for(int i=0;i<envelopes.length;i++){
            if(map.containsKey(envelopes[i][0])){
                TreeSet<Integer> set=map.get(envelopes[i][0]);
                set.add(envelopes[i][1]);
            }else{
                TreeSet<Integer> set=new TreeSet();
                set.add(envelopes[i][1]);
                map.put(envelopes[i][0],set);
            }
        }
        //ArrayList<Integer> list=new ArrayList<>();
        int lastValue=0,index=0,count=0;
        for(Map.Entry<Integer,TreeSet<Integer>> entry:map.entrySet()){
            Integer key=entry.getKey();
            TreeSet<Integer> value=entry.getValue();
            if(index==0){
                lastValue=value.first();
                count++;
            }else{
                if(value.size()>1){
                    //find the smallest value which is larger than lastValue
                    for(Integer i:value){
                        if(i>lastValue){
                            count++;
                            lastValue=i;
                            break;
                        }
                    }
                }else{
                    if(value.first()>lastValue){
                        count++;
                        lastValue=value.first();
                    }
                }
            }
            index++;
        }
        return count;
    }
}
