package cn.allay.api.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * String helpers
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
@UtilityClass
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

    public static String repeat(String string, int count) {
        if (count <= 1) return count == 0 ? "" : string;

        var len = string.length();
        var longSize = (long) len * (long) count;
        var size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }

        char[] array = new char[size];
        string.getChars(0, len, array, 0);

        int n;
        for (n = len; n < size - n; n <<= 1) System.arraycopy(array, 0, array, n, n);

        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }
}
