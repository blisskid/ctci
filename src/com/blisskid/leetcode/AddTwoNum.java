package com.blisskid.leetcode;

public class AddTwoNum {


    public static void main(String[] args) {
        // write your code here
        AddTwoNum temp = new AddTwoNum();
//        ListNode l1 = null;
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);
//        l1.next.next = new ListNode(3);

//        ListNode l2 = null;
        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        ListNode res = temp.addTwoNumbers(l1, l2);
        System.out.println("S");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException();
        }
        Boolean ifMoreThanTen = false;
        ListNode resNode = new ListNode(-1);
        ListNode resultNode = resNode;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (ifMoreThanTen) {
                sum++;
                ifMoreThanTen = false;
            }
            if (sum >= 10) {
                ifMoreThanTen = true;
                sum -= 10;
            }
            resNode.val = sum;

            if (null != l1.next || null != l2.next) {
                resNode.next = new ListNode(-1);
                resNode = resNode.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        if (null == l1 && null != l2) {
            while (null != l2) {
                int sum = l2.val;

                if (ifMoreThanTen) {
                    sum++;
                    ifMoreThanTen = false;
                }
                if (sum >= 10) {
                    ifMoreThanTen = true;
                    sum -= 10;
                }
                resNode.val = sum;

                if (l2.next != null) {
                    resNode.next = new ListNode(-1);
                    resNode = resNode.next;

                }
                l2 = l2.next;
            }
        }

        if (null == l2 && null != l1) {
            while (null != l1) {
                int sum = l1.val;

                if (ifMoreThanTen) {
                    sum++;
                    ifMoreThanTen = false;
                }
                if (sum >= 10) {
                    ifMoreThanTen = true;
                    sum -= 10;
                }
                resNode.val = sum;

                if (l1.next != null) {
                    resNode.next = new ListNode(-1);
                    resNode = resNode.next;

                }
                l1 = l1.next;
            }
        }

        if (null == l2 && null == l1 && ifMoreThanTen) {
            resNode.next = new ListNode(1);
            resNode = resNode.next;
        }

        return resultNode;
    }
}


