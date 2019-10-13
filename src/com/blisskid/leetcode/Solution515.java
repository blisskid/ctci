package com.blisskid.leetcode;

import java.util.*;


public class Solution515 {

    public static void main(String[] args) {
        Solution515 solution = new Solution515();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        root.right.left = null;
        System.out.println(solution.largestValues(root));
        //solution.print(root, 1);
    }


    private int level = 0;

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new ArrayList<Integer>();
        traverse(root, 1);
        for (Integer i : map.values()) {
            resultList.add(i);
        }
        return resultList;
    }

    private void traverse(TreeNode node, int level) {

        if (null != node) {
            if ( null == map.get(level) || (null != map.get(level) && node.val > map.get(level))) {
                map.put(level, node.val);
            }
            level++;
            traverse(node.left, level);
            traverse(node.right, level);
        }
    }

    private void print(TreeNode node, int level) {
        if (null != node) {
            System.out.println(level + ":" + node.val);
            level++;
            print(node.left, level);
            print(node.right, level);

        }
    }
}
