package com.blisskid.leetcode.list;

import java.util.*;

class Skiplist {

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(9);
        skiplist.add(4);
        skiplist.add(5);
        skiplist.add(6);
        skiplist.add(9);
        System.out.println(skiplist.erase(2));
        System.out.println(skiplist.erase(1));
        skiplist.add(2);
        System.out.println(skiplist.search(7));
        System.out.println(skiplist.search(4));
        skiplist.add(5);
        System.out.println(skiplist.erase(6));
        System.out.println(skiplist.search(5));
        skiplist.add(6);
        skiplist.add(7);
        skiplist.add(4);
        System.out.println(skiplist.erase(3));
        System.out.println(skiplist.search(6));//true
        System.out.println(skiplist.search(3));
        System.out.println(skiplist.search(4));
        System.out.println(skiplist.search(3));
        System.out.println(skiplist.search(8));
        System.out.println(skiplist.erase(7));
        System.out.println(skiplist.erase(6));
        System.out.println(skiplist.search(7));
        System.out.println(skiplist.erase(4));
        skiplist.add(1);
        skiplist.add(6);
        System.out.println(skiplist.erase(3));
        skiplist.add(4);
        System.out.println(skiplist.search(7));
        System.out.println(skiplist.search(6));
        System.out.println(skiplist.search(1));
        System.out.println(skiplist.search(0));
        System.out.println(skiplist.search(3));
    }

    private LinkedList<MyList> matrix;

    public Skiplist() {
        matrix = new LinkedList();
    }

    private int get(int target) {
        MyList myList = matrix.getLast();
        //iterate mylist to check
        Node itr = myList.head;
        while (itr != null) {
            if (target == itr.val) {
                //find the index from top to bottom
                Node temp = itr;
                while (temp.down != null) {
                    temp = temp.down;
                }
                return temp.index;
            }
            if (target < itr.val) {
                //get the pre node, dig to level 0
                if (itr.pre == null) {
                    Node temp = itr;
                    //find level 1 index
                    while (temp.down != null) {
                        temp = temp.down;
                    }
                    while (temp.pre != null) {
                        temp = temp.pre;
                        if (temp.val == target)
                            return temp.index;
                    }
                } else {
                    Node temp = itr.pre;
                    //find level 1 index
                    while (temp.down != null) {
                        temp = temp.down;
                    }
                    //find from temp index to right
                    while (temp.next != null) {
                        temp = temp.next;
                        if (temp.val == target) {
                            return temp.index;
                        }
                    }
                }
                return -1;
            }
            itr = itr.next;
        }
        if (itr == null) {
            Node temp = myList.tail;
            while (temp.down != null) {
                temp = temp.down;
            }
            while (temp.next != null) {
                temp = temp.next;
                if (temp.val == target) {
                    return temp.index;
                }
            }
        }
        return -1;
    }

    public boolean search(int target) {
        return get(target) != -1;
    }

    public void add(int num) {
        if (matrix.size() == 0) {
            MyList levelOne = new MyList();
            levelOne.addAtTail(num);
            matrix.add(levelOne);
        } else {
            //find the position to insert
            int index = findPos(num);
            MyList levelOne = matrix.getFirst();
            Node lastNode = levelOne.addAtIndex(index, num);
            Node currentNode = null;
            Iterator<MyList> itr = matrix.iterator();
            itr.next();
            Boolean coinFlag = false;
            while (itr.hasNext()) {
                coinFlag = coinFlip();
                if (coinFlag) {
                    MyList tempList = itr.next();
                    index = findPosFromList(tempList, num);
                    currentNode = tempList.addAtIndex(index, num);
                    lastNode.up = currentNode;
                    currentNode.down = lastNode;
                    lastNode = currentNode;
                } else {
                    break;
                }
            }
            //itr is the top level or coinFlag is false
            if (!coinFlag) {
                //itr is the top level
                while (coinFlip()) {
                    MyList temp = new MyList();
                    currentNode = temp.addAtHead(num);
                    matrix.add(temp);
                    lastNode.up = currentNode;
                    currentNode.down = lastNode;
                    lastNode = currentNode;
                }
            }
        }
    }

    //first search it, than delete it
    public boolean erase(int num) {
        int index = get(num);
        if (index != -1) {
            MyList firstList = matrix.getFirst();
            Node nodeForDel = firstList.get(index);
            for (MyList ele : matrix) {
                ele.deleteAtIndex(index);
                if (nodeForDel.up != null) {
                    nodeForDel = nodeForDel.up;
                    index = nodeForDel.index;
                } else {
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    private int findPosFromList(MyList tempList, int num) {
        Node itr = tempList.head;
        while (itr != null && num > itr.val) itr = itr.next;
        if (itr == null) {
            //num is greater than all numbers in tempList
            return tempList.size;
        } else {
            //itr.val is equal or greater than val,insert before itr
            return itr.index;
        }
    }
    //return the index to insert
    private int findPos(int num) {
        MyList myList = matrix.getLast();
        //iterate mylist to check
        Node itr = myList.head;
        while (itr != null) {
            if (num <= itr.val) {
                //find the index from top to bottom
                Node temp = itr;
                while (temp.down != null) {
                    temp = temp.down;
                }
                if (num == temp.val) {
                    return temp.index;
                } else {
                    //num < temp.val
                    while (temp.pre != null) {
                        temp = temp.pre;
                        if (num > temp.val) {
                            return temp.next.index;
                        }

                    }
                }

            }
            itr = itr.next;
        }
        //add after tail
        return matrix.getFirst().size;
    }
    //return the index to insert
    //record the left element
    //return list, list.get(0) is the last top down index, list.get(1) is the index to insert
    private List<Integer> findPos1(int num) {
        List<Integer> result = new ArrayList();
        MyList myList = matrix.getLast();
        //iterate mylist to check
        Node itr = myList.head;
        while (itr != null) {
            if (num == itr.val) {
                //find the index from top to bottom
                Node temp = itr;
                while (temp.down != null) {
                    temp = temp.down;
                }
                result.add(temp.index - 1);
                //insert the val before index
                result.add(temp.index);
                return result;
            }
            if (num < itr.val) {
                //get the pre node, dig to level 0
                if (itr.pre == null) {
                    /*
                    Node temp = itr;
                    //find level 1 index
                    while (temp.down != null) {
                        temp = temp.down;
                    }
                    result.add(temp.index);
                    while (temp.pre != null) {
                        temp = temp.pre;
                        if (temp.val >= num)
                            return temp.index;
                    }
                    return -1;
                    */
                    result.add(-1);
                    result.add(0);
                    return result;
                } else {
                    Node temp = itr.pre;
                    //find level 1 index
                    while (temp.down != null) {
                        temp = temp.down;
                    }
                    result.add(temp.index);
                    //find from temp index to right
                    while (temp.next != null) {
                        temp = temp.next;
                        if (temp.val >= num) {
                            result.add(temp.index);
                            return result;
                        }
                    }
                }
            }
            itr = itr.next;
        }
        //add after tail
        result.add(matrix.getFirst().size - 1);
        result.add(matrix.getFirst().size);
        return result;
    }

    private boolean coinFlip() {
        return Math.random() < 0.5;
    }


    private class Node {
        private int val;
        private int index;
        private Node next;
        private Node pre;
        private Node up;
        private Node down;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
            this.next = null;
            this.pre = null;
            this.up = null;
            this.down = null;
        }
    }

    private class MyList {

        private int size;
        private Node tail;
        private Node head;

        /**
         * Initialize your data structure here.
         */
        public MyList() {
            this.size = 0;
            this.tail = null;
            this.head = null;
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public Node get(int index) {
            Node itr = head;
            while (itr != null) {
                if (itr.index == index) {
                    return itr;
                }
                itr = itr.next;
            }
            return null;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public Node addAtHead(int val) {
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
            return this.head;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public Node addAtTail(int val) {
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
            return this.tail;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public Node addAtIndex(int index, int val) {
            if (index == this.size) {
                return addAtTail(val);
            } else if (index > this.size) {
                return null;
            } else if (index == 0) {
                return this.addAtHead(val);
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
                        return node;
                    }
                    itr = itr.next;
                }
                return null;
            }
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
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


}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */