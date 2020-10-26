package com.blisskid.leetcode.tree;

public class S0208M {

    public static void main(String[] args) {
        S0208M trie = new S0208M();

//        trie.insert("apple");
//        trie.search("apple");   // returns true
//        trie.search("app");     // returns false
//        trie.startsWith("app");       // returns true
//        trie.insert("app");
//        System.out.println(trie.search("app"));    // returns true
        System.out.println(trie.startsWith("a"));

    }


    private TreeNode root;

    private class TreeNode {

        private char c;
        private TreeNode[] children;
        /**
         * Initialize your data structure here.
         */
        public TreeNode(char c) {
            this.c = c;
            this.children = new TreeNode[27];
        }

        public TreeNode() {
            this.c = '0';
            this.children = new TreeNode[27];
        }

        private TreeNode addChild(TreeNode child) {
            int index = child.c - 'a';
            if (children[index] == null) {
                children[index] = child;
            }
            return children[index];
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word, TreeNode root) {
            TreeNode temp = root;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                TreeNode t = new TreeNode(arr[i]);
                temp = temp.addChild(t);
            }
            temp.children[26] = new TreeNode();
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word, TreeNode root) {
            char[] arr = word.toCharArray();
            TreeNode temp = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (temp.children[index] != null && temp.children[index].c == arr[i]) {
                    temp = temp.children[index];
                } else {
                    return false;
                }
            }

            if (temp.children[26] == null) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix, TreeNode root) {
            char[] arr = prefix.toCharArray();
            TreeNode temp = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (temp.children[index] != null && temp.children[index].c == arr[i]) {
                    temp = temp.children[index];
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Initialize your data structure here.
     */

    public S0208M() {
        root = new TreeNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        this.root.insert(word, this.root);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return this.root.search(word, this.root);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return this.root.startsWith(prefix, this.root);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */