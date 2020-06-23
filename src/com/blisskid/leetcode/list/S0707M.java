package com.blisskid.leetcode.list;

public class S0707M {

    public static void main(String[] args) {

    }

    private class Node {
        public int val;
        public int index;
        public Node next;
        public Node pre;
        public Node(int val, int index) {
            this.val = val;
            this.index = index;
            this.next = null;
            this.pre = null;
        }
    }

    private int size;
    private Node tail;
    private Node head;

    /** Initialize your data structure here. */
    public S0707M() {
        this.size = 0;
        this.tail = null;
        this.head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node itr = head;
        while (itr != null) {
            if (itr.index == index) {
                return itr.val;
            }
            itr = itr.next;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if (this.size == 0) {
            this.tail = new Node(val, 0);
            this.head = this.tail;
        } else {
            Node temp = new Node(val, 0);
            head.pre = temp;
            temp.next = head;
            head = temp;
            //update index
            Node itr = head;
            while (itr.next != null) {
                itr = itr.next;
                itr.index++;
            }
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (this.size == 0) {
            this.tail = new Node(val, 0);
            this.head = this.tail;
        } else {
            Node temp = new Node(val, tail.index++);
            temp.pre = tail;
            tail.next = temp;
            tail = temp;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == tail.index + 1) {
            addAtTail(val);
        } else if (index > tail.index + 1) {
            return;
        } else {
            Node itr = head;
            while (itr != null) {
                if (itr.index == index) {
                    //insert before index-th node, which is itr
                    Node node = new Node(val, index);
                    node.pre = itr.pre;
                    node.next = itr;
                    itr.pre = node;
                    //update index
                    Node tempItr = itr;
                    while (tempItr != null) {
                        tempItr.index++;
                        tempItr = tempItr.next;
                    }
                    return;
                }
                itr = itr.next;
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index > tail.index) {
            return;
        } else {
            Node itr = head;
            while (itr != null) {
                if (itr.index == index) {
                    //delete itr
                    itr.pre.next = itr.next;
                    itr.next.pre = itr.pre;
                    //update index;
                    Node tempItr = itr.next;
                    while (tempItr != null) {
                        tempItr.index--;
                        tempItr = tempItr.next;
                    }
                    itr = null;
                    return;
                }
                itr = itr.next;
            }
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
