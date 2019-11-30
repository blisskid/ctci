package com.blisskid.leetcode.other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S1080 {

    public static void main(String[] args) {
        S1080 c = new S1080();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-5);
        root.right.left = new TreeNode(4);
        c.sufficientSubset(root, -1);
    }

    private Set<TreeNode> nodeSet = new HashSet<TreeNode>();
    private int limit;

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) return null;
        this.limit = limit;
        List<TreeNode> route = new ArrayList();
        traverse(root, route);
        TreeNode result = null;
        if (nodeSet.contains(root)) {
            result = new TreeNode(root.val);
            copyAndRemove(root, result);
        }
        return result;
    }

    private void traverse(TreeNode node, List<TreeNode> route) {
//        TreeNode tempNode = new TreeNode(node.val);
        route.add(node);

        if (node.left == null && null == node.right) {
            //the node is leaf,test if the sum is less than limit
            int sum = 0;
            for (int i = 0; i < route.size(); i++) {
                sum += route.get(i).val;
            }
            if (sum >= limit) {
                for (int i = 0; i < route.size(); i++) {
                    nodeSet.add(route.get(i));
                }
            }
        } else {
            if (node.left != null) {
                traverse(node.left, route);
            }
            if (node.right != null) {
                traverse(node.right, route);
            }
        }
        route.remove(node);
    }

    private void copyAndRemove(TreeNode node, TreeNode result) {
        if (node.left != null && nodeSet.contains(node.left)) {
            result.left = new TreeNode(node.left.val);
            copyAndRemove(node.left, result.left);
        }
        if (node.right != null && nodeSet.contains(node.right)) {
            result.right = new TreeNode(node.right.val);
            copyAndRemove(node.right, result.right);
        }
    }
}
