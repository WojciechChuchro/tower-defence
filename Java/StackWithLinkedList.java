package Java;

public class StackWithLinkedList {
    private Node top;
    private Node bottom;
    private int length;

    public StackWithLinkedList() {
        this.top = null;
        this.bottom = null;
        this.length = 0;
    }

    public void peek() {
        if (this.top != null) {
            System.out.println("Top value is: " + top.value);
        } else {
            System.out.println("There is not top value");
        }
    }

    public void push(float value) {
        Node newTop = new Node(value);
        if (this.length == 0) {
            this.top = newTop;
            this.bottom = newTop;
        } else {
            Node holdingPointer = this.top;
            this.top = newTop;
            this.top.next = holdingPointer;
        }
        this.length++;
    }

    public void pop() {
        if (this.top != null) {
            this.top = this.top.next;
            this.length--;
            if (this.length == 0) {
                this.bottom = null;
            }
        }
    }

    public void isEmpty() {
        if (this.length == 0) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Stack is not empty");
        }
    }

    public static void main(String[] args) {
        StackWithLinkedList myStackWithLinkedList = new StackWithLinkedList();
        myStackWithLinkedList.push(15);
        myStackWithLinkedList.push(25);
        myStackWithLinkedList.pop();
        myStackWithLinkedList.pop();
        myStackWithLinkedList.peek();
        myStackWithLinkedList.isEmpty();
    }
}

