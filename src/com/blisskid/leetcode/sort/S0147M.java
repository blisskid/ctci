package com.blisskid.leetcode.sort;

public class S0147M {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        new S0147M().insertionSortList(head);
    }


    public ListNode insertionSortList(ListNode head) {
        //from head to tail
        ListNode itr = head;
        int size = 0;
        while (itr != null) {
            itr = itr.next;
            size++;
        }

        int[] a = new int[size];

        int index = 0;
        itr = head;
        while (itr != null) {
            a[index] = itr.val;
            index++;
            itr = itr.next;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    int tmp = a[i];
                    for (int k = i; k > j; k--) {
                        a[k] = a[k - 1];
                    }
                    a[j] = tmp;
                    break;
                }
            }
        }
        ListNode result = null;
        if (size > 0) result = new ListNode(a[0]);
        itr = result;
        for (int i = 1; i < size; i++) {
            itr.next = new ListNode(a[i]);
            itr = itr.next;
        }

        return result;
    }
}
