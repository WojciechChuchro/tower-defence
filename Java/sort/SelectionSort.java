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
            int index = i;
            T minElement = array[i];

            for (int j = i; j < length; j++) {
                if (minElement.compareTo(array[j]) > 0) {
                    index = j;
                    minElement = array[j];
                }
            }

            if (index != i) {
                T temp = array[i];
                array[i] = minElement;
                array[index] = temp;
            }
        }
    }
}
