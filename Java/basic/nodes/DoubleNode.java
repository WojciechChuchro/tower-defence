package Java.basic.nodes;

public class DoubleNode {
    public double value;
    public DoubleNode next;

    public DoubleNode previous;

    public DoubleNode(double value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }
}