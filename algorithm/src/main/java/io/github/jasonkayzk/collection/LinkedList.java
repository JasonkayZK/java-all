package io.github.jasonkayzk.collection;

public class LinkedList<E> {

    static class Node<E> {
        E data;

        Node<E> next;
        Node<E> prev;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }
    }

    private int length;

    private Node<E> head;

    private Node<E> tail;

    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public E peekFirst() {
        if (head == null) return null;
        return head.data;
    }

    public E peekLast() {
        if (tail == null) return null;
        return tail.data;
    }

    public E get(int idx) {
        if (idx < 0) return null;

        Node<E> cur = this.head;
        // todo
        return null;
    }

    public void set(int idx) {

    }

    public void addFirst(E e) {

    }

    public void addLast(E e) {

    }

    public void add(E e, int idx) {

    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(length - 1);
    }

    public E remove(int idx) {
        // todo
        return null;
    }

    public long getLength() {
        return length;
    }
}
