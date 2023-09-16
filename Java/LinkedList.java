package Java;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(double value) {
        this.head = new Node(value);
        this.tail = this.head;
        this.length = 1;
    }

    private static class Node {
        public double value;
        public Node next;

        Node(double value) {
            this.value = value;
            this.next = null;
        }
    }

    public void append(double value) {
        Node newNode = new Node(value);
        this.tail.next = newNode;
        this.tail = newNode;
        this.length++;
    }

    public void prepend(double value) {
        Node newNode = new Node(value);
        newNode.next = this.head;
        this.head = newNode;
        this.length++;
    }

    public void insert(double value, int index) {
        Node newNode = new Node(value);
        if (index == 0) {
            this.prepend(newNode.value);
        } else {
            Node currentNode = lookup(index - 1);
            if (currentNode != null) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                this.length++;
            }
        }
    }

    public void remove(int index) {
        if (index == 0) {
            this.head = this.head.next;
        } else {
            Node previousNode = this.lookup(index - 1);
            Node nextNode = this.lookup(index + 1);
            if (previousNode == null) {
                return;
            }

            previousNode.next = nextNode;
        }
        this.length--;
    }

    private Node lookup(int index) {
        if (this.length < index || index < 0) {
            return null;
        }

        Node currentNode = this.head;

        for (int i = 0; i != index; i++ ) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void printList() {
        Node currentNode = this.head;
        System.out.print("HEAD -> ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList(10);
        myLinkedList.append(5);
        myLinkedList.append(20);
        myLinkedList.insert(25, 2);
//        myLinkedList.remove(0);
        myLinkedList.printList();
    }
}