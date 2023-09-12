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
        LinkedList myLinkedList = new LinkedList(10F);
        myLinkedList.append(5);
        myLinkedList.append(12.3);
        myLinkedList.append(18.3);
        myLinkedList.append(16);
        myLinkedList.prepend(1);
        myLinkedList.prepend(2);
        myLinkedList.prepend(3);

        myLinkedList.printList();
    }
}
