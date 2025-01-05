package Java.NeetCode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("neet","code","love","you"));
        System.out.println(encode(list));
        System.out.println(decode(encode(list)));

    }
    public static String encode(List<String> strs) {

        StringBuilder result = new StringBuilder();
        for(String s : strs) {
            result.append(s).append("#4");
        }

        return result.toString();
    }

    public static List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#' && str.charAt(i+1) == '4') {
                i++;
                result.add(string.toString());
                string.delete(0, string.length());
            } else {
                string.append(str.charAt(i));
            }
        }

        return result;
    }
}
