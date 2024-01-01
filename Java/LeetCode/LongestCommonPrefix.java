package Java.LeetCode;

/**
 * Finds the longest common prefix string among an array of strings.
 * If there is no common prefix, returns an empty string "".<br><br>
 *
 * Example 1:<br>
 * Input: strs = ["flower","flow","flight"]<br>
 * Output: "fl"<br><br>
 *
 * Example 2:<br>
 * Input: strs = ["dog","racecar","car"]<br>
 * Output: ""<br>
 * Explanation: There is no common prefix among the input strings.<br><br>
 *
 * Constraints:<br>
 * - 1 <= strs.length <= 200<br>
 * - 0 <= strs[i].length <= 200<br>
 * - strs[i] consists of only lowercase English letters.
 */

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strings) {
        // Check if the input array is null or empty
        if (strings == null || strings.length == 0) {
            return "";
        }

        // Check if the length of the array exceeds the constraint
        if (strings.length >= 200) {
            return "";
        }

        // Check if the first character of the first string is an empty string
        if (strings[0].isEmpty()) {
            return "";
        }

        String prefix = String.valueOf(strings[0].charAt(0));
        int j = 0;


        while (true) {
            for (String string : strings) {
                // Check if the length of the string exceeds the constraint
                if (string.length() >= 200) {
                    return "";
                }

                // Check if the string contains only lowercase letters
                if (!hasLowercaseLetters(string)) {
                    return "";
                }

                // Check if the current position exceeds the length of the current string
                if (j > string.length()) {
                    return prefix;
                }

                // Check if the current string does not start with the current prefix
                if (!string.startsWith(prefix)) {
                    return prefix.substring(0, prefix.length() - 1);
                }
            }

            j++;
            if (j < strings[0].length()) {
                prefix += String.valueOf(strings[0].charAt(j));
            }
        }
    }

    public static boolean hasLowercaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLowerCase(string.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
