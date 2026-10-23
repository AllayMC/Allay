package org.allaymc.dependence;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code StringUtils} splits generator input names without treating delimiters
 * as regular expressions. The list splitter consumes one character at each
 * match and retains empty segments, while the two-part splitter consumes the
 * complete delimiter and supplies a default prefix when none is present.
 *
 * @author daoge_cmd
 */
public class StringUtils {
    /**
     * Splits the input into a new mutable list using one-character delimiters.
     * Empty segments are retained, including a trailing empty segment; this
     * overload uses the largest supported part limit rather than discarding the
     * unsplit suffix.
     */
    public static List<String> fastSplit(String str, String delimiter) {
        return fastSplit(str, delimiter, Integer.MAX_VALUE);
    }

    /**
     * Splits the input into at most the requested number of retained segments.
     * The last segment contains any unsplit suffix. Each match consumes one
     * character regardless of delimiter length, so callers use a single
     * character for conventional splitting; empty segments are preserved.
     *
     * @throws IllegalArgumentException if {@code limit} is not greater than one
     */
    public static List<String> fastSplit(String str, String delimiter, int limit) {
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

    /**
     * Splits at the first literal delimiter into a new two-element array. A
     * missing delimiter leaves the whole input in the second element, while a
     * leading delimiter leaves an empty prefix; both cases use the supplied
     * default as the first element. Later delimiters remain in the suffix.
     */
    public static String[] fastTwoPartSplit(String str, String delimiter, String defaultPartOne) {
        String[] strings = new String[]{defaultPartOne, str};
        int i = str.indexOf(delimiter);
        if (i >= 0) {
            strings[1] = str.substring(i + delimiter.length());
            if (i >= 1) {
                strings[0] = str.substring(0, i);
            }
        }
        return strings;
    }
}
