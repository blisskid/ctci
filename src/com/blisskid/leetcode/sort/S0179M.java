package com.blisskid.leetcode.sort;

import java.util.*;
/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 *
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class S0179M {

    public static void main(String[] args) {
        int[] nums1 = new int[]{9051,5526,2264,5041,1630,5906,6787,8382,4662,4532,6804,4710,4542,2116,7219,8701,8308,957,8775,4822,396,8995,8597,2304,8902,830,8591,5828,9642,7100,3976,5565,5490,1613,5731,8052,8985,2623,6325,3723,5224,8274,4787,6310,3393,78,3288,7584,7440,5752,351,4555,7265,9959,3866,9854,2709,5817,7272,43,1014,7527,3946,4289,1272,5213,710,1603,2436,8823,5228,2581,771,3700,2109,5638,3402,3910,871,5441,6861,9556,1089,4088,2788,9632,6822,6145,5137,236,683,2869,9525,8161,8374,2439,6028,7813,6406,7519};
        int[] nums = new int[]{12, 121};
        System.out.println(new S0179M().largestNumber(nums));
    }

    private class Pair {
        public Integer num;
        public Integer modifiedNum;

        Pair (Integer num, Integer modifiedNum) {
            this.num = num;
            this.modifiedNum = modifiedNum;
        }
    }

    public String largestNumber(int[] nums) {
        List<Pair> listArray[] = new ArrayList[10];

        for (int i = 0; i < 10; i++) {
            listArray[i] = new ArrayList<Pair>();
        }

        for (int num : nums) {
            int index = getFirst(num);
            listArray[index].add(new Pair(num, num));
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            if (listArray[i].size() > 0) {

                //get the max length
                Integer max = -1;

                for (Pair ele : listArray[i]) {
                    max = Math.max(ele.num, max);
                }
                int maxLen = max.toString().length();
                for (Pair ele : listArray[i]) {
                    ele.modifiedNum = trans(ele.num, maxLen, i);
                }

                //sort by modifiedNum
                listArray[i].sort(new Comparator<Pair>() {
                    public int compare(Pair o1, Pair o2) {
                        if (o1.modifiedNum < o2.modifiedNum) {
                            return 1;
                        } else if (o1.modifiedNum > o2.modifiedNum) {
                            return -1;
                        } else {
                            //equal
                            if (o1.num < 10 || o2.num < 10) {
                                return 1;
                            }
                            if (o1.num.toString().charAt(1) > o1.num.toString().charAt(0)) {
                                //put the smaller at first
                                if (o1.num < o2.num) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            } else {
                                //put the larger at first
                                if (o1.num < o2.num) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    }
                });

                for (Pair ele : listArray[i]) {
                    sb.append(ele.num.toString());
                }

            }
        }

        int index = 0;
        while (index < sb.length() - 1 && sb.charAt(index) == '0') {
            index++;
        }

        return sb.substring(index);
    }

    //if num's size is less than maxLen, add cont utill the size is equal to maxLen
    private Integer trans(Integer num, int maxLen, int cont) {
        int len = num.toString().length();
        StringBuilder sb = new StringBuilder(num.toString());
        if (len < maxLen) {
            for (int i = 0; i < maxLen - len; i++) {
                sb.append(Integer.toString(cont));
            }
        }
        return Integer.parseInt(sb.toString());
    }

    //get the first int of num
    private int getFirst(int num) {
        while (num >= 10) {
            num = num / 10;
        }
        return num;
    }

}
