package Java.sort;

public class InsertionSort implements ISort {

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        int length = array.length;

        for (int i = 1; i < length; i++) {
            T currentElement = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(currentElement) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = currentElement;
        }
    }
}