package Java;

import Java.nodes.BinarySearchNode;

public class BinarySearchTree {
    private BinarySearchNode root;
    BinarySearchTree() {
        this.root = null;
    }

    public void insertWithoutRecuresion(double value) {
        BinarySearchNode newNode = new BinarySearchNode(value);
        if (this.root == null) {
            this.root = newNode;
        } else {
            BinarySearchNode iterator = this.root;
            while (true) {
                if (iterator.value == value) {
                    System.out.println("The value is already added");
                    break;
                }
                if (iterator.value < value) {
                    if (iterator.right == null) {
                        iterator.right = newNode;
                        break;
                    }
                    iterator = iterator.right;
                }
                if (iterator.value > value) {
                    if (iterator.left == null) {
                        iterator.left = newNode;
                        break;
                    }
                    iterator = iterator.left;
                }
            }
        }
    }

    public void insert(double value) {
        this.root = insert(this.root, value);
    }

    public BinarySearchNode insert(BinarySearchNode node, double value) {
        if (node == null) {
            node = new BinarySearchNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        return node;
    }

    public boolean lookup(double value) {
        return lookup(root, value);
    }

    public boolean lookup(BinarySearchNode node, double value) {
            if (node == null) {
                return false;
            }

            if (node.value == value) {
                return true;
            } else if (node.value < value) {
                return lookup(node.right, value);
            } else {
                return lookup(node.left, value);
            }
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(BinarySearchNode node, int depth) {
        if (node == null) {
            return;
        }

        printTree(node.right, depth + 1);

        for (int i = 0; i < depth * 4; i++) {
            System.out.print(" ");
        }
        System.out.println(node.value);
        printTree(node.left, depth + 1);
    }



    public static void main(String[] args) {
        BinarySearchTree myBinarySearchTree = new BinarySearchTree();
        myBinarySearchTree.insert(15);
        myBinarySearchTree.insert(10);
        myBinarySearchTree.insert(11);
        myBinarySearchTree.insert(7);
        myBinarySearchTree.insert(25);
        myBinarySearchTree.insert(24);
        myBinarySearchTree.insert(35);
        myBinarySearchTree.insert(45);
        myBinarySearchTree.insert(26);
        myBinarySearchTree.printTree();
        System.out.println(myBinarySearchTree.lookup(34232));
    }
}


