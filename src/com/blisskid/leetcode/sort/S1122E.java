package com.blisskid.leetcode.sort;
import java.util.*;
/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *  
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *  
 *
 * Constraints:
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */

public class S1122E {

    public static void main(String[] args) {
        S1122E s = new S1122E();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] result = s.relativeSortArray(arr1, arr2);
        System.out.println(result);
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, List<Integer>> map = new LinkedHashMap<Integer, List<Integer>>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], new ArrayList<Integer>());
        }
        //in arr1 not in arr2
        List<Integer> otherList = new ArrayList<Integer>();

        for (int i = 0; i < arr1.length; i++) {
            List<Integer> list = map.get(arr1[i]);
            if (list != null)
                list.add(arr1[i]);
            else {
                otherList.add(arr1[i]);
            }
        }

        otherList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        List<Integer> resultList = new ArrayList<Integer>();

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            for (Integer ele : list) {
                resultList.add(ele);
            }
        }
        resultList.addAll(otherList);
        Integer[] resultArray = new Integer[resultList.size()];
        resultList.toArray(resultArray);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultArray[i];
        }
        return result;
    }
}
