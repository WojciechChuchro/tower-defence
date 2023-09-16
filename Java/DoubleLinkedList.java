package Java;

public class DoubleLinkedList {
    private Node head;
    private Node tail;
    private int length;

    public DoubleLinkedList(double value) {
        this.head = new Node(value);
        this.tail = this.head;
        this.length = 1;
    }

    private static class Node {
        public double value;
        public Node next;

        public Node previous;

        Node(double value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    public void append(double value) {
        Node newNode = new Node(value);
        this.tail.next = newNode;
        this.tail.next.previous = this.tail;
        this.tail = newNode;
        this.length++;
    }

    public void prepend(double value) {
        Node newNode = new Node(value);
        newNode.next = this.head;
        this.head.previous = newNode;
        this.head = newNode;
        this.length++;
    }

    public void insert(double value, int index) {
        if (isIndexInvalid(index)) {
            return;
        }

        Node newNode = new Node(value);
        if (index == 0) {
            this.prepend(newNode.value);
        } else {
            Node currentNode = lookup(index - 1);
            if (currentNode != null) {
                newNode.next = currentNode.next;
                newNode.previous = currentNode;
                currentNode.next = newNode;
                if (newNode.next != null) {
                    newNode.next.previous = newNode;
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
        }

        else if (index == this.length - 1) {
            this.tail = this.tail.previous;
            if (this.tail != null) {
                this.tail.next = null;
            } else {
                this.head = null;
            }
        }

        else {
            Node prevNode = lookup(index - 1);
            if (prevNode == null || prevNode.next == null) {
                return;
            }

            Node nodeToRemove = prevNode.next;
            Node nextNode = nodeToRemove.next;

            prevNode.next = nextNode;

            if (nextNode != null) {
                nextNode.previous = prevNode;
            }
        }

        this.length--;
    }


    private Node lookup(int index) {
        if (isIndexInvalid(index)) {
            return null;
        }

        Node currentNode;
        currentNode = this.head;

        for (int i = 0; i != index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    public void printListFromHead() {
        Node currentNode = this.head;
        System.out.print("HEAD -> ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("NULL");
    }

    public void printListFromTail() {
        Node currentNode = this.tail;
        System.out.print("TAIL -> ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.previous;
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
    }
}