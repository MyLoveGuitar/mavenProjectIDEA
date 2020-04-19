package com.test.skipList;

public class SkipListNode<T> {
    int key;
    T value;
    SkipListNode<T> left, right, up, down;

    public SkipListNode(int key, T value) {
        this.key = key;
        this.value = value;
    }

    public static final int HEAD_KEY = Integer.MIN_VALUE;
    public static final int TAIL_KEY = Integer.MAX_VALUE;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
