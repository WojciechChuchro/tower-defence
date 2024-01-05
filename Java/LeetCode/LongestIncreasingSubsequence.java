package Java.LeetCode;

import java.util.Arrays;

/**
 * Given an integer array nums, this function returns the length of the longest strictly increasing subsequence. <br><br>
 *
 * Example 1: <br>
 * Input: nums = [10,9,2,5,3,7,101,18] <br>
 * Output: 4 <br>
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. <br><br>
 *
 * Example 2: <br>
 * Input: nums = [0,1,0,3,2,3] <br>
 * Output: 4 <br>
 * Explanation: The longest increasing subsequence is [0,1,2,3], therefore the length is 4. <br><br>
 *
 * Example 3: <br>
 * Input: nums = [7,7,7,7,7,7,7] <br>
 * Output: 1 <br>
 * Explanation: Each element in the array is the same, forming a subsequence of length 1. <br><br>
 *
 */
public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
