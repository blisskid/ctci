package com.blisskid.leetcode.tree;

import java.util.*;

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
//        System.out.println(c.bfs(c.root));
//        c.deserialize(",7,3,NULL,NULL,15,9,NULL,NULL,20,NULL,NULL");
        c.deBfs(",7,3,15,NULL,NULL,9,20,NULL,NULL,NULL,NULL");
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
            List<String> list = new LinkedList<>();
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

    private String bfs(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> l = new LinkedList<TreeNode>();
        l.add(root);
        while (l.size() > 0) {
            TreeNode node = l.remove();
            if (node == null) {
                sb.append(",NULL");
            }
            else {
                sb.append("," + node.val);
                l.add(node.left);
                l.add(node.right);
            }
        }
        return sb.toString();
    }

    private TreeNode deBfs(String data) {
        if (data == null || data.length() == 0) return null;
        String[] strArray = data.split(",");
        TreeNode tree = null;
        if (!strArray[1].equals("NULL")) {
            Queue<TreeNode> list = new LinkedList();
            for (String s : strArray) {
                if (s.length() > 0) {
                    if (s.equals("NULL"))
                        list.add(null);
                    else
                        list.add(new TreeNode(Integer.parseInt(s)));
                }
            }
            //tree = build(list);
            Queue<TreeNode> l = new LinkedList();
            tree = list.element();
            l.offer(list.remove());
            while (list.size() > 0) {
                TreeNode temp = l.remove();
                temp.left = list.remove();
                if (temp.left != null) {
                    l.offer(temp.left);
                }
                temp.right = list.remove();
                if (temp.right != null) {
                    l.offer(temp.right);
                }
            }
        }
        return tree;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));