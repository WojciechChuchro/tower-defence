package Java;

public class DoubleNode {
    public double value;
    public DoubleNode next;

    public DoubleNode previous;

    DoubleNode(double value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }
}