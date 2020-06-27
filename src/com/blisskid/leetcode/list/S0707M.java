package com.blisskid.leetcode.list;

public class S0707M {

    public static void main(String[] args) {
        S0707M s = new S0707M();
        s.addAtHead(1);
        s.addAtTail(3);
//        s.addAtIndex(0, 10);
//        s.addAtIndex(0, 20);
//        s.addAtIndex(1, 30);
        s.addAtIndex(1, 2);
        System.out.println(s.get(1));
        s.deleteAtIndex(1);
//        s.deleteAtIndex(0);
        System.out.println(s.get(1));
    }

    private class Node {
        private int val;
        private int index;
        private Node next;
        private Node pre;
        private Node(int val, int index) {
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
            this.size++;
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
            this.size++;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (this.size == 0) {
            this.tail = new Node(val, 0);
            this.head = this.tail;
            this.size++;
        } else {
            Node temp = new Node(val, tail.index + 1);
            temp.pre = tail;
            tail.next = temp;
            tail = temp;
            this.size++;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == this.size) {
            addAtTail(val);
        } else if (index > this.size) {
            return;
        } else if (index == 0) {
            this.addAtHead(val);
        } else {
            Node itr = head;
            while (itr != null) {
                if (itr.index == index) {
                    //insert before index-th node, which is itr
                    Node node = new Node(val, index);
                    node.pre = itr.pre;
                    node.next = itr;
                    itr.pre = node;
                    node.pre.next = node;
                    //update index
                    Node tempItr = itr;
                    while (tempItr != null) {
                        tempItr.index++;
                        tempItr = tempItr.next;
                    }
                    this.size++;
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
                    if (itr.pre != null) {
                        itr.pre.next = itr.next;
                    } else {
                        //delete head
                        head = itr.next;
                    }

                    if (itr.next != null)
                        itr.next.pre = itr.pre;
                    else
                        tail = itr.pre;
                    //update index;
                    if (itr.next != null) {
                        Node tempItr = itr.next;
                        while (tempItr != null) {
                            tempItr.index--;
                            tempItr = tempItr.next;
                        }
                    }
                    itr = null;
                    this.size--;
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
