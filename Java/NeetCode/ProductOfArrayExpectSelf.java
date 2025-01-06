package Java.NeetCode;

import java.util.Arrays;

public class ProductOfArrayExpectSelf {
    public static void main(String[] args) {
        int[] nums = {1,2,4,6};

        System.out.println(Arrays.toString(productExceptSelfPrefixSuffix(nums)));
    }

    public static int[] productExceptSelfPrefixSuffix(int[] nums) {
        int[] result = new int[nums.length];
        int prefix = 1;
        int postfix = 1;

        for(int i = 0; i < nums.length; i++) {
            if (i != 0) {
                prefix = prefix * nums[i - 1];
            }
            result[i] = prefix;
        }

        for(int i = nums.length-1; i >= 0; i--) {
                if (i != nums.length - 1) {
                    postfix = postfix * nums[i + 1];
                }
                result[i] = postfix * result[i];
        }

        return result;
    }
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = 1;
            for (int j = 0; j < nums.length; j++) {
                if(i != j) {
                    num *= nums[j];
                }
            }

            result[i] = num;
        }

        return result;
    }
}
