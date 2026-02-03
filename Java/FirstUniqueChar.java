import java.util.*;

public class FirstUniqueChar {

    public static char find(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : map.keySet())
            if (map.get(c) == 1) return c;

        return '_';
    }

    public static void main(String[] args) {
        System.out.println(find("aabbcddee"));
    }
}
