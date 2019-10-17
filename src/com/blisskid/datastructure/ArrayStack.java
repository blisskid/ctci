package com.blisskid.datastructure;

import java.util.HashMap;

public class ArrayStack<E> {

    private E[] data;

    private int t = -1;

    public static final int CAPACITY = 1000;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int size) {
        this.data = (E[]) new Object[size];
    }

    public int size() {
        return t + 1;
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E result = this.data[t];
        this.data[t--] = null;
        return result;
    }

    public void push(E e) {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        this.data[++t] = e;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }


}
