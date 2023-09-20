package Java.nodes;

public class BinarySearchNode {
    public double value;
    public BinarySearchNode left;
    public BinarySearchNode right;

    BinarySearchNode() {
        this.left = null;
        this.right = null;
        this.value = 0;
    }
    public BinarySearchNode(double value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }
}