package com.blisskid.leetcode.tree;

class S0105M {

    public static void main(String[] args) {
//        int[] preorder = new int[]{3,9,4,20,15,7};
//        int[] inorder = new int[]{4,9,3,15,20,7};

        int[] preorder = new int[]{4,1,3,2};
        int[] inorder = new int[]{1,2,3,4};
        TreeNode tree = new S0105M().buildTree(preorder, inorder);
        System.out.println(tree.val);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0) return null;
        return build(preorder, 0, length - 1, inorder, 0, length - 1, -1);
    }

    private TreeNode build(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2, int offset) {
//        int l = inorder.length - 1;
//        if (s1 < 0 || s2 < 0 || e1 < 0 || e2 < 0 || s1 > l || s2 >l || e1 > l || e2 > l) return null;
        if (s1 > e1 || s2 > e2) return null;
        if (s1 == e1) return new TreeNode(preorder[s1]);
        if (s2 == e2) return new TreeNode(inorder[s2]);
        //find inorder's root place
        TreeNode root = new TreeNode(preorder[s1]);
        offset++;
        int inIndex = 0;
        for (int i = s2; i <= e2; i++) {
            if (root.val == inorder[i]) {
                inIndex = i;
                break;
            }
        }
        int preIndex = inIndex + offset;
        root.left = build(preorder, s1 + 1, preIndex, inorder, s2, inIndex - 1, offset);
        root.right = build(preorder, preIndex + 1, e1, inorder, inIndex + 1, e2, offset - 1);
        //root.left = build(preorder, s1 + 1, s1 + rIndex, inorder, s2, rIndex - 1);
        //root.right = build(preorder, rIndex + 1, e1, inorder, s1 + rIndex + 1, e2);
        return root;
    }
}


class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
