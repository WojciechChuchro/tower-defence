package Java.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, returns a string array answer (1-indexed) where:<br>
 * - answer[i] == "FizzBuzz" if i is divisible by 3 and 5.<br>
 * - answer[i] == "Fizz" if i is divisible by 3.<br>
 * - answer[i] == "Buzz" if i is divisible by 5.<br>
 * - answer[i] == i (as a string) if none of the above conditions are true.<br><br>
 * Example 1:<br>
 * Input: n = 3<br>
 * Output: ["1","2","Fizz"]<br><br>
 * Example 2:<br>
 * Input: n = 5<br>
 * Output: ["1","2","Fizz","4","Buzz"]<br><br>
 * Example 3:<br>
 * Input: n = 15<br>
 * Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]<br><br>
 */

public class FizzBuzz {
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            }

            else if(i % 3 == 0) {
                result.add("Fizz");
            }

            else if(i % 5 == 0) {
                result.add("Buzz");
            }

            else {
                result.add(i + "");
            }
        }

        return result;
    }
}
