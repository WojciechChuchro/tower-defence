package Java.NeetCode;

import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) {
        String s = "jam", t = "jar";
        System.out.println(isAnagram(s, t));
    }
    public static boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        if(sArr.length != tArr.length){
            return false;
        }

        Arrays.sort(sArr);
        Arrays.sort(tArr);


        System.out.println(new String(sArr));
        System.out.println(new String(tArr));

        return new String(sArr).equals(new String(tArr));
    }
}
