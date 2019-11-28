package com.blisskid.leetcode.stack;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class S1249 {


    public static void main(String[] args) {
        System.out.println(new S1249().minRemoveToMakeValid("(a(b(c)d)"));
    }

    private class Stack {
        private Pair<Integer, Character>[] arr = null;

        private int index = 0;
        Stack(int size) {
            arr = new Pair[size];
        }

        public void push(Pair p) {
            arr[index++] = p;
        }

        public Pair[] getArray() {
            return arr;
        }

        public Pair pop() {
            if (empty()) {
                return null;
            }
            Pair result = arr[--index];
            arr[index] = null;
            return result;
        }

        public boolean empty() {
            return index == 0;
        }

        public Pair top() {
            if (empty()) {
                return null;
            }
            return arr[index - 1];
        }
    }

    public String minRemoveToMakeValid(String s) {
        Set<Integer> mySet = new HashSet<Integer>();
        Stack stack = new Stack(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                stack.push(new Pair(i, chars[i]));
                mySet.add(i);
            } else if (')' == chars[i]) {
                if (!stack.empty() && stack.top().getValue().equals('(')) {
                    mySet.remove(stack.top().getKey());
                    stack.pop();
                } else {
                    stack.push(new Pair(i, chars[i]));
                    mySet.add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (!mySet.contains(i)) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
