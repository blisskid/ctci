package com.blisskid.leetcode.greedy;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */

public class S0135H {
    public static void main(String[] args) {
        System.out.println(new S0135H().candy(new int[]{0,1,2,3,2,1}));
    }

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                //i is smaller or equal than i - 1
                candies[i] = 1;
                if (ratings[i - 1] > ratings[i] && candies[i - 1] == 1) {
                    candies[i - 1] = 2;
                    for (int j = i - 2; j >= 0 && ratings[j] > ratings[j + 1] && candies[j] <= candies[j + 1]; j--) {
                        candies[j] = candies[j + 1] + 1;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < candies.length; i++) {
            sum += candies[i];
        }
        return sum;
    }
}
