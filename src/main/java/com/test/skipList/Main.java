package com.test.skipList;

public class Main {
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<String>();
        skipList.put(1, "1");
        skipList.put(5, "5");
        skipList.put(3, "4");
        skipList.put(2, "2");
        skipList.put(6, "6");
        skipList.put(8, "8");

        System.out.println(skipList.toString2());

        SkipListNode<String> search = skipList.search(5);
        System.out.println(search.key);
    }
}
