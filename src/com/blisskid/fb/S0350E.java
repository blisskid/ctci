package com.blisskid.fb;
import java.util.*;

public class S0350E {

    public static void main(String[] args) {
        int[] nums1=new int[]{1,2,2,1};
        int[] nums2=new int[]{2,2};

        System.out.println(new S0350E().intersect(nums1,nums2));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int size1=nums1.length;
        int size2=nums2.length;
        List<Integer> resList=null;
        int maxSize=0;
        for(int i=0;i<size1;i++){
            for(int j=0;j<size2;j++){
                List<Integer> list=new ArrayList();
                int index1=i;
                int index2=j;
                while(index1<size1&&index2<size2&&nums1[index1]==nums2[index2]){
                    list.add(nums1[index1]);
                    index1++;
                    index2++;
                }
                if(maxSize<list.size()){
                    maxSize=list.size();
                    resList=new ArrayList();
                    resList.addAll(list);
                }
            }
        }
        int[] result=new int[maxSize];
        for(int i=0;i<maxSize;i++){
            result[i]=resList.get(i);
        }
        return result;
    }
}
