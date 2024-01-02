package Java.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums, create a 2D array satisfying the following conditions: <br><br>
 *
 * 1. The 2D array should contain only the elements of the array nums.<br>
 * 2. Each row in the 2D array contains distinct integers.<br>
 * 3. The number of rows in the 2D array should be minimal.<br><br>
 *
 * If there are multiple valid answers, any of them can be returned.<br><br>
 *
 * Example 1:<br>
 * Input: nums = [1,3,4,1,2,3,1]<br>
 * Output: [[1,3,4,2],[1,3],[1]]<br>
 * Explanation: We can create a 2D array that contains the following rows:<br>
 * - 1,3,4,2<br>
 * - 1,3<br>
 * - 1<br>
 * All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.<br>
 * It can be shown that we cannot have less than 3 rows in a valid array.<br><br>
 *
 * Example 2:<br>
 * Input: nums = [1,2,3,4]<br>
 * Output: [[4,3,2,1]]<br>
 * Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.<br><br>
 *
 * Constraints:<br>
 * 1 <= nums.length <= 200<br>
 * 1 <= nums[i] <= nums.length<br>
 */
public class ConvertArrayInto2DArrayWithConditions {
    public static List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(List.of()));
        int counter = 1;

        for(int num : nums) {
            for(List<Integer> list : result) {
                if (list.contains(num) && result.size() == counter) {
                    result.add(new ArrayList<>(List.of(num)));
                    break;
                }
                else if (!list.contains(num)){
                    list.add(num);
                    break;
                }
                counter++;
            }
            counter = 1;
        }

        return result;
    }
}
