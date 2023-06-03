package cn.allay.dependence;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * String helpers
 */
public class StringUtils {
    public static List<String> fastSplit(String str, String delimiter) {
        return fastSplit(str, delimiter, Integer.MAX_VALUE);
    }

    public static List<String> fastSplit(String str, String delimiter, int limit) {
        //limit should bigger than 1
        if (limit <= 1) throw new IllegalArgumentException("limit should bigger than 1");
        var tmp = str;
        var results = new ArrayList<String>();
        var count = 1;
        while (true) {
            int j = tmp.indexOf(delimiter);
            if (j < 0) {
                results.add(tmp);
                break;
            }
            results.add(tmp.substring(0, j));
            count++;
            tmp = tmp.substring(j + 1);
            if (count == limit || tmp.isEmpty()) {
                results.add(tmp);
                break;
            }
        }
        return results;
    }

    public static String[] fastTwoPartSplit(String str, String delimiter, String defaultPartOne) {
        String[] strings = new String[]{defaultPartOne, str};
        int i = str.indexOf(delimiter);
        if (i >= 0) {
            strings[1] = str.substring(i + 1);
            if (i >= 1) {
                strings[0] = str.substring(0, i);
            }
        }
        return strings;
    }
}
