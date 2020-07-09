package com.blisskid.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class S0449M {
    public static void main(String[] args) {
        S0449M c = new S0449M();
//        System.out.println(c.serialize(c.root));
        c.deserialize(",7,3,NULL,NULL,15,9,NULL,NULL,20,NULL,NULL");
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode root;

    S0449M() {
        root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append("," + root.val);
            sb.append(serialize(root.left));
            sb.append(serialize(root.right));
        } else {
            sb.append(",NULL");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArray = data.split(",");
        TreeNode tree = null;
        if (!strArray[1].equals("NULL")) {
            List<String> list = new ArrayList<>();
            for (String s : strArray) {
                if (s.length() > 0) {
                    list.add(s);
                }

            }
            tree = build(list);
        }
        return tree;
    }

    private TreeNode build(List<String> a) {
        if (null == a || a.size() == 0) return null;
        TreeNode tree = null;
        String s = a.get(0);
        a.remove(0);
        if (!s.equals("NULL")) {
            tree = new TreeNode(Integer.parseInt(s));
            tree.left = build(a);
            tree.right = build(a);
        }
        return tree;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));