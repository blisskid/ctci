package com.blisskid.leetcode;
import java.util.*;
class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.put(3, 3); // cache is {1=1, 2=2}
        lRUCache.put(4, 4); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(4));    // returns 4
        System.out.println(lRUCache.get(3));    // returns 3
        System.out.println(lRUCache.get(2));    // returns 2
        System.out.println(lRUCache.get(1));    // returns -1
        lRUCache.put(5, 5); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(2));    // return 2
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return -1
        System.out.println(lRUCache.get(5));    // return 5
    }
    private DoubleLinkedList list;
    private HashMap<Integer,Node> map;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.list=new DoubleLinkedList(capacity);
        this.map=new HashMap();
        this.capacity=capacity;
        this.size=0;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            this.list.moveBeforeHead(node);
            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            //update and move to Head
            Node node=map.get(key);
            node.value=value;
            map.put(key,node);
            list.moveBeforeHead(node);
        }else{
            //add new node
            Node node=new Node(key,value);
            if(size<capacity){
                //can add,only add it to the head
                map.put(key,node);
                list.addFirst(node);
                size++;
            }else{
                //add and remove last
                Node tail=list.tail;
                map.remove(list.tail.key);
                list.removeLast();
                list.addFirst(node);
                map.put(key,node);
            }
        }
    }
}

class DoubleLinkedList{
    public Node head,tail;
    public int capacity;

    public DoubleLinkedList(int capacity){
        this.capacity=capacity;
        this.head=null;
        this.tail=null;
    }

    //add the node before head
    public void addFirst(Node node){
        node.next=head;
        if(head!=null)
            head.pre=node;
        head=node;
        if(tail==null){
            tail=node;
        }
    }

    public void removeLast(){
        if(tail.pre==null){
            //no pre
            tail=null;
            head=null;
        }else{
            tail.pre.next=null;
            tail=tail.pre;
        }
    }

    //move the node before the head
    public void moveBeforeHead(Node node){
        if(node.pre!=null){
            if(node.next==null){
                tail.next=head;
                //tail.pre=null;
                head.pre=tail;
                Node temp=tail.pre;
                //head.next=null;
                head=tail;
                tail=temp;
                head.pre=null;
                tail.next=null;
            }else{
                node.pre.next=node.next;
                node.next.pre=node.pre;
                node.next=head;
                head.pre=node;
                node.pre=null;
                head=node;
            }

        }
    }
}

class Node{
    public int key;
    public int value;
    public Node pre,next;
    public Node(int key,int value){
        this.key=key;
        this.value=value;
    }
}
