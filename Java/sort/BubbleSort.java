package Java.sort;

import java.util.Collections;
import java.util.List;

public class BubbleSort {
    public static List<Integer> sort(List<Integer> array) {
        int length = array.size();

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    Collections.swap(array, j, j + 1);
                }
            }
        }

        return array;
    }
}