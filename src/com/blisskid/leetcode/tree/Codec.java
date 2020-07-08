package com.blisskid.leetcode.tree;

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
public class Codec {
    public static void main(String[] args) {
        Codec c = new Codec();
        System.out.println(c.serialize(c.root));
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

    Codec() {
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
        Stack<String> s = new Stack<>();
        String[] strArray = data.split(",");
        TreeNode tree = null;
        if (!strArray[1].equals("NULL")) {
            tree = new TreeNode(Integer.parseInt(strArray[1]));
            s.push(strArray[1]);
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("NULL")) {
                    if (s.peek().equals("NULL")) {
                        s.pop();
                        TreeNode temp = new TreeNode(Integer.parseInt(s.pop()));
                        temp.left = null;
                        temp.right = null;
                    } else {
                        s.push(strArray[i]);
                    }
                } else {
                    s.push(strArray[i]);
                }
            }
        }
        return tree;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));