//Given an integer x, return true if x is a palindrome, and false otherwise.
//
//        Example 1:
//        Input: x = 121
//        Output: true
//        Explanation: 121 reads as 121 from left to right and from right to left.
//
//        Example 2:
//        Input: x = -121
//        Output: false
//        Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore, it is not a palindrome.

//        Example 3:
//        Input: x = 10
//        Output: false
//        Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.

// Follow up: Could you solve it without converting the integer to a string?


package Java.LeetCode;

import java.util.ArrayList;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        int originalX = x;

        while (x > 0) {
            arrayList.add(x % 10);
            x = x / 10;
        }

        int powerOfTen = 1;
        int reverse = 0;

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            reverse += arrayList.get(i) * powerOfTen;
            powerOfTen *= 10;
        }

        return reverse == originalX;
    }
}


