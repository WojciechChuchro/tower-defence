package Java.LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given arrays representing the start times, end times, and profits of n jobs, this function returns the maximum profit achievable
 * by selecting a subset of non-overlapping jobs. Each job is scheduled to be done from startTime[i] to endTime[i], and has a
 * corresponding profit[i].
 * <br>
 * <br>
 * <b>Example 1:</b>
 * <br>Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * <br>Output: 120
 * <br>Explanation: The subset chosen is the first and fourth job.
 * <br>Time range [1-3] + [3-6], resulting in a profit of 120 = 50 + 70.
 *
 * <br>
 * <br>
 * <b>Example 2:</b>
 * <br>Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * <br>Output: 150
 * <br>Explanation: The subset chosen is the first, fourth, and fifth job.
 * <br>Profit obtained is 150 = 20 + 70 + 60.
 *
 * <br>
 * <br>
 * <b>Example 3:</b>
 * <br>Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * <br>Output: 6
 */

public class MaximumProfitJobScheduling {
    public static void main(String[] args) {
        int[] startTime = {1, 2, 3, 4, 6};
        int[] endTime = {3, 5, 10, 6, 9};
        int[] profit = {20, 20, 100, 70, 60};

        System.out.println(jobScheduling(startTime, endTime, profit));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));

        int[] dp = new int[n];
        dp[0] = jobs[0][2];

        for (int i = 1; i < n; i++) {
            int currentProfit = jobs[i][2];
            int prevNonOverlap = binarySearch(jobs, i);
            if (prevNonOverlap != -1) {
                currentProfit += dp[prevNonOverlap];
            }
            dp[i] = Math.max(dp[i - 1], currentProfit);
        }

        return dp[n - 1];
    }

    private static int binarySearch(int[][] jobs, int index) {
        int low = 0, high = index - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (jobs[mid][1] <= jobs[index][0]) {
                if (jobs[mid + 1][1] <= jobs[index][0]) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
