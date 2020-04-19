package com.test.skipList;

import java.util.Random;

public class SkipList<T> {
    private SkipListNode<T> head;
    private SkipListNode<T> tail;
    //the number of nodes
    private int nodes = 0;
    //index height
    int highLevel = 0;
    Random random;
    private static final double PROBABILITY = 0.5;

    public SkipList() {
        random = new Random();
        clear();
    }

    private void clear() {
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
        horizontalLink(head, tail);
        nodes = 0;
        highLevel = 0;
    }

    /**
     * if contains key return node,else return prefer node
     *
     * @param key
     * @return
     */
    public SkipListNode<T> findNode(int key) {
        SkipListNode<T> p = head;
        SkipListNode<T> node = p;
        while (true) {
            while (p.right.key != SkipListNode.TAIL_KEY && p.right.key <= key) {
                p = p.right;
            }
            if (p.down != null) {
                p = p.down;
            } else {
                return p;
            }
        }

    }

    /**
     * search node form SkipList ,return the suit node if existd,else return null
     *
     * @param key
     * @return
     */
    public SkipListNode<T> search(int key) {
        SkipListNode<T> node = findNode(key);
        if (key == node.key)
            return node;
        else
            return null;
    }

    public void put(int key, T value) {
        SkipListNode<T> p = findNode(key);
        if (p.key == key) {
            p.value = value;
            return;
        }

        int high = 0;

        SkipListNode<T> q = new SkipListNode<T>(key, value);
        backLink(p, q);

        while (random.nextDouble() < PROBABILITY) {
            if (high >= highLevel) {
                highLevel++;
                SkipListNode<T> h1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> t1 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);

                horizontalLink(h1, t1);
                verticalLink(h1, head);
                verticalLink(t1, tail);
                head = h1;
                tail = t1;
            }


            while (p.up == null) {
                p = p.left;
            }

            p = p.up;
            SkipListNode<T> e = new SkipListNode<T>(key, null);
            backLink(p, e);
            verticalLink(e, q);
            q = e;
            high++;
        }

        nodes++;
    }

    private void backLink(SkipListNode<T> front, SkipListNode<T> newNode) {
        newNode.right = front.right;
        front.right.left = newNode;
        front.right = newNode;
        newNode.left = front;
    }

    private void horizontalLink(SkipListNode<T> l, SkipListNode<T> r) {
        l.right = r;
        r.left = l;
    }

    private void verticalLink(SkipListNode<T> up, SkipListNode<T> down) {
        up.down = down;
        down.up = up;
    }

    public int size() {
        return nodes;
    }

    @Override
    public String toString() {
        if (size() == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        SkipListNode<T> p = head;
        while (p.down != null) {
            p = p.down;
        }
        while (p.left != null) {
            p = p.left;
        }

        if (p.right != null) {
            p = p.right;
        } else {
            return null;
        }

        builder.append("[");
        while (p.right != null) {
            builder.append(p.value).append(",");
            p = p.right;
        }
        builder.append("]");

        return builder.toString();
    }

    public String toString2() {
        if (size() == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        SkipListNode<T> q = head;
        SkipListNode<T> p = q;

        while (p != null) {
            p = p.right;
            builder.append("[");
            while (p.right != null) {
                builder.append(p.key).append(",");
                p = p.right;
            }
            builder.append("]\r\n");
            if (q.down == null)
                break;
            p = q.down;
            q = p;
        }

        return builder.toString();
    }
}
