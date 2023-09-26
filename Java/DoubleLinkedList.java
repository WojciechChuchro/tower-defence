package Java;

import Java.nodes.DoubleNode;

public class DoubleLinkedList {
    private DoubleNode head;
    private DoubleNode tail;
    private int length;

    public DoubleLinkedList(double value) {
        this.head = new DoubleNode(value);
        this.tail = this.head;
        this.length = 1;
    }


    public void append(double value) {
        DoubleNode newDoubleNode = new DoubleNode(value);
        this.tail.next = newDoubleNode;
        this.tail.next.previous = this.tail;
        this.tail = newDoubleNode;
        this.length++;
    }

    public void prepend(double value) {
        DoubleNode newDoubleNode = new DoubleNode(value);
        newDoubleNode.next = this.head;
        this.head.previous = newDoubleNode;
        this.head = newDoubleNode;
        this.length++;
    }

    public void insert(double value, int index) {
        if (isIndexInvalid(index)) {
            return;
        }

        DoubleNode newDoubleNode = new DoubleNode(value);
        if (index == 0) {
            this.prepend(newDoubleNode.value);
        } else {
            DoubleNode currentDoubleNode = lookup(index - 1);
            if (currentDoubleNode != null) {
                newDoubleNode.next = currentDoubleNode.next;
                newDoubleNode.previous = currentDoubleNode;
                currentDoubleNode.next = newDoubleNode;
                if (newDoubleNode.next != null) {
                    newDoubleNode.next.previous = newDoubleNode;
                }
                this.length++;
            }
        }
    }

    public void remove(int index) {
        if (isIndexInvalid(index)) {
            return;
        }

        if (index == 0) {
            this.head = this.head.next;
            if (this.head != null) {
                this.head.previous = null;
            } else {
                this.tail = null;
            }
        } else if (index == this.length - 1) {
            this.tail = this.tail.previous;
            if (this.tail != null) {
                this.tail.next = null;
            } else {
                this.head = null;
            }
        } else {
            DoubleNode prevDoubleNode = lookup(index - 1);
            if (prevDoubleNode == null || prevDoubleNode.next == null) {
                return;
            }

            DoubleNode doubleNodeToRemove = prevDoubleNode.next;
            DoubleNode nextDoubleNode = doubleNodeToRemove.next;

            prevDoubleNode.next = nextDoubleNode;

            if (nextDoubleNode != null) {
                nextDoubleNode.previous = prevDoubleNode;
            }
        }

        this.length--;
    }


    private DoubleNode lookup(int index) {
        if (isIndexInvalid(index)) {
            return null;
        }

        DoubleNode currentDoubleNode;
        currentDoubleNode = this.head;

        for (int i = 0; i != index; i++)
            currentDoubleNode = currentDoubleNode.next;
        return currentDoubleNode;
    }

    public void reverse() {
        DoubleNode current = this.head;
        DoubleNode temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        while (current != null) {
            DoubleNode nextNode = current.next;
            current.next = current.previous;
            current.previous = nextNode;

            current = nextNode;
        }
    }


    public void printListFromHead() {
        DoubleNode currentDoubleNode = this.head;
        System.out.print("HEAD -> ");
        while (currentDoubleNode != null) {
            System.out.print(currentDoubleNode.value + " -> ");
            currentDoubleNode = currentDoubleNode.next;
        }
        System.out.println("NULL");
    }

    public void printListFromTail() {
        DoubleNode currentDoubleNode = this.tail;
        System.out.print("TAIL -> ");
        while (currentDoubleNode != null) {
            System.out.print(currentDoubleNode.value + " -> ");
            currentDoubleNode = currentDoubleNode.previous;
        }
        System.out.println("NULL");
    }

    public boolean isIndexInvalid(int index) {
        return index < 0 || index >= this.length;
    }


    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList(10);
        doubleLinkedList.append(5);
        doubleLinkedList.append(15);
        doubleLinkedList.prepend(99);
        doubleLinkedList.insert(25, 1);
        doubleLinkedList.remove(1);
        doubleLinkedList.printListFromHead();
        doubleLinkedList.printListFromTail();
        doubleLinkedList.reverse();
        doubleLinkedList.printListFromHead();
    }
}