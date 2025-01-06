package Java.NeetCode;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 2, 3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> arr = new ArrayList<>();
        int[] result = new int[k];

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr.add(new int[] {entry.getValue(), entry.getKey()});
        }
        System.out.println("arr");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }

        arr.sort((a, b) -> b[0] - a[0]);
        System.out.println("arr sorted");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        for (int i = 0; i < k; i++) {
            result[i] = arr.get(i)[1];
        }
        return result;
    }
}
