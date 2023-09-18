package Java;

public class StackWithArray {
    private final float[] array;

    private int index;

    public StackWithArray(int length) {
        this.array = new float[length];
        this.index = 0;
    }

    public void peek() {
        if (index > 0) {
            System.out.println(this.array[index - 1]);
        } else {
            System.out.println("Stack is empty");
        }
    }

    public void push(float value) {
        if (this.index < this.array.length) {
            this.array[index] = value;
            index++;
        } else {
            System.out.println("Index is greater than array length");
        }
    }

    public void pop() {
        if (index != 0) {
            index--;
            this.array[index] = 0;
        } else {
            System.out.println("There is nothing to delete");
        }
    }

    public void isEmpty() {
        if (this.index == 0) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Stack is not empty");
        }
    }

    public static void main(String[] args) {
        StackWithArray myStackWithArray = new StackWithArray(10);
        myStackWithArray.push(15);
        myStackWithArray.push(25);
        myStackWithArray.peek();
        myStackWithArray.pop();
        myStackWithArray.pop();
        myStackWithArray.isEmpty();
    }
}

