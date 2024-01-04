package Java.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a 0-indexed array nums consisting of positive integers, this class provides a method to determine <br>
 * the minimum number of operations required to make the array empty using two types of operations: <br>
 * 1. Choose two elements with equal values and delete them from the array. <br>
 * 2. Choose three elements with equal values and delete them from the array. <br><br>
 *
 * <b>Example 1:</b> <br>
 * Input: nums = [2,3,3,2,2,4,2,3,4] <br>
 * Output: 4 <br>
 * Explanation: <br>
 * - Apply the first operation on the elements at indices 0 and 3, resulting in [3,3,2,4,2,3,4]. <br>
 * - Apply the first operation on the elements at indices 2 and 4, resulting in [3,3,4,3,4]. <br>
 * - Apply the second operation on the elements at indices 0, 1, and 3, resulting in [4,4]. <br>
 * - Apply the first operation on the elements at indices 0 and 1, resulting in an empty array. <br>
 * It can be shown that we cannot make the array empty in less than 4 operations. <br><br>
 *
 * <b>Example 2: <br>
 * Input: nums = [2,1,2,2,3,3] <br>
 * Output: -1 <br>
 * Explanation: It is impossible to empty the array. <br>
 */
public class MinimumNumberOperationsMakeArrayEmpty {
    public static int minOperations(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int result = 0;

        Arrays.stream(nums).forEach(num -> {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.put(num, hashMap.get(num) + 1);
            }
        });

        for (int value: hashMap.values()) {
            if (value == 1) {
                return -1;
            } else if (value % 3 == 0) {
                result += value / 3;
            } else if (value % 3 == 1) {
                result += (value / 3) + 1;
            } else if (value % 3 == 2) {
                result += (value / 3) + 1;
            } else {
                result = -1;
                break;
            }
        }

        return result;
    }
}
