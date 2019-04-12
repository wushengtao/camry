package com.lunzi.camry.sort;

/**
 * 单链表
 * 双链表
 * Created by lunzi on 2019/4/10 11:21 AM
 */
public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    private synchronized void tailAdd(T value) {
        //判断有无初始化
        if (head == null) {
            //初始化
            head = new Node<>();
            head.next = null;
            head.pre = null;
            tail = head;
        }
        //建立新的节点
        Node<T> newNode = new Node<>();
        newNode.setValue(value);

        //找出最后一个节点
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        newNode.pre = node;
        tail.next = newNode;
        tail = newNode;
    }

    //删除最后一个节点
    private synchronized void remove() {
        //找到倒数第二个节点 next=null
        Node node = head;
        while (node.next != null && node.next.next != null) {
            node = node.next;
        }
        node.next = null;
        tail = node;

    }

    private synchronized void del(T value) {

    }

    private synchronized void printList(Node head) {
        while (head.next != null) {
            System.out.println("value：" + getValue(head) + "next:" + getValue(head.next) + "pre:" + getValue(head.pre));
            head = head.next;
        }
        System.out.println("value：" + getValue(head) + "next:" + getValue(head.next) + "pre:" + getValue(head.pre));
    }

    private T getValue(Node<T> node) {
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    public class Node<T> {
        T value;
        //后继节点
        Node<T> next;
        Node<T> pre;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPre() {
            return pre;
        }

        public void setPre(Node<T> pre) {
            this.pre = pre;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.tailAdd(1);
        linkedList.tailAdd(2);
        linkedList.tailAdd(3);
        linkedList.tailAdd(4);
        linkedList.tailAdd(5);
        linkedList.tailAdd(6);
        linkedList.printList(linkedList.head);
    }
}
