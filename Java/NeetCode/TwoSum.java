package Java.NeetCode;

import java.util.HashMap;
import java.util.Optional;

public class TwoSum {
    public static void main(String[] args) {
        int [] nums = {3,4,5,6};
        int target = 7;


        TwoSum.twoSum(nums, target);
    }
    public static int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            Optional<Integer> val = Optional.ofNullable(map.get(target-nums[i]));

            if(val.isEmpty()) {
                map.put(nums[i],i);
            } else {
                return new int[]{val.get(),i};
            }

        }
        return null;
    }
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
