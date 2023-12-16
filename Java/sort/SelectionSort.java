package Java.sort;

import java.util.Arrays;

public class SelectionSort implements ISort {

    public static void main(String[] args) {
        SortConstant sortConstant = new SortConstant();
        SelectionSort selectionSort = new SelectionSort();


        selectionSort.sort(sortConstant.stringArray);
        System.out.println("Sorted String Array: " + Arrays.toString(sortConstant.stringArray));

        selectionSort.sort(sortConstant.intArray);
        System.out.println("Sorted Integer Array: " + Arrays.toString(sortConstant.intArray));
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        int length = array.length;

        for (int i = 0; i < length; i++) {
            int min = i;
            T temp = array[i];

            for (int j = i + 1; j < length; j++) {
                if (array[min].compareTo(array[j]) > 0) {
                    min = j;
                }
            }

            array[i] = array[min];
            array[min] = temp;
        }
    }
}