package Java;

import Java.nodes.Node;

public class Queue {
    private Node first;
    private Node last;
    private int length;
    public Queue() {
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public void peek() {
        if (this.first != null) {
            System.out.println("Top value is: " + first.value);
        } else {
            System.out.println("There is not top value");
        }
    }

    public void enqueue(float value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.first = newNode;
        } else {
            this.last.next = newNode;
        }
        this.last = newNode;
        this.length++;
    }

    public void dequeue() {
        if (this.first != null) {
            this.first = this.first.next;
            this.length--;

            if (this.length == 0) {
                this.last = null;
            }
        }
    }

    public void isEmpty() {
        if (this.length == 0) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Queue is not empty");
        }
    }

    public static void main(String[] args) {
        Queue myQueue = new Queue();
        myQueue.enqueue(15);
        myQueue.enqueue(25);
        myQueue.dequeue();
        myQueue.peek();
        myQueue.dequeue();
        myQueue.isEmpty();
    }
}
