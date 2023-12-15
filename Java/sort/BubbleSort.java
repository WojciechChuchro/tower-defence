package Java.sort;

import java.util.Arrays;

public class BubbleSort implements ISort {

    public static void main(String[] args) {
        SortConstant sortConstant = new SortConstant();
        BubbleSort bubbleSort = new BubbleSort();

        bubbleSort.sort(sortConstant.stringArray);
        System.out.println("Sorted String Array: " + Arrays.toString(sortConstant.stringArray));

        bubbleSort.sort(sortConstant.intArray);
        System.out.println("Sorted Integer Array: " + Arrays.toString(sortConstant.intArray));
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if ((array[j]).compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }
}