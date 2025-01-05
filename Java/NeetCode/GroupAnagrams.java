package Java.NeetCode;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"stop","pots","reed","","tops","deer","opts",""};

        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int a = 0;
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if (!map.containsKey(Arrays.toString(chars))) {
                map.put(Arrays.toString(chars), a);
                a++;
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
            } else {
                result.get(map.get(Arrays.toString(chars))).add(str);
            }
        }
        System.out.println(result);

        return result;
    }
}
