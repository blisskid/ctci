package com.blisskid.leetcode.stack;


public class S0844 {
    private class Stack {
        private Character[] arr = null;

        private int index = 0;
        Stack(int size) {
            arr = new Character[size];
        }

        public void push(Character c) {
            arr[index++] = c;
        }

        public Character[] getArray() {
            return arr;
        }

        public Character pop() {
            if (empty()) {
                return null;
            }
            Character result = arr[--index];
            arr[index] = null;
            return result;
        }

        public boolean empty() {
            return index == 0;
        }

        public Character top() {
            if (empty()) {
                return null;
            }
            return arr[index - 1];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < index; i++) {
                sb.append(arr[i]);
            }
            return sb.toString();
        }
    }

    public boolean backspaceCompare(String S, String T) {
        return format(S).equals(format(T));
    }

    private String format(String s) {
        char[] sArr = s.toCharArray();
        Stack stack = new Stack(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (sArr[i] == '#') {
                stack.pop();
            } else {
                stack.push(sArr[i]);
            }
        }
        return stack.toString();
    }
}