package com.blisskid.leetcode.heap;

public class Test {
    public static void main(String[] args) {
        Son b = new Son();
        Father a = b;
        b.F();
        a.F();

        String str1 = "hello world";
        String str2 = "hello world";
        String str3 = "he" + new String("llo");
        System.err.println(str1 == str2);
        System.err.println(str1 == str3);
    }
}

class Father {
    public void F() {
        System.out.println("A.F");
    }

}


class Son extends Father {
    public void F() {
        System.out.println("B.F");
    }
}