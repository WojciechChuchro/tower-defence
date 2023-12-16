package Java.sort;

public class SelectionSort implements ISort {

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