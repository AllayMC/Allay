package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * AllayStringUtils provides some string helper methods.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class AllayStringUtils {
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
            strings[1] = str.substring(i + delimiter.length());
            if (i >= 1) {
                strings[0] = str.substring(0, i);
            }
        }
        return strings;
    }

    /**
     * Parses the given text to split command arguments
     *
     * @param cmdLine the command line
     * @return a List of command arguments
     */
    public static LinkedList<String> splitCommandArgs(String cmdLine) {
        StringBuilder sb = new StringBuilder(cmdLine);
        LinkedList<String> args = new LinkedList<>();
        boolean notQuoted = true;
        int curlyBraceCount = 0;
        int start = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (notQuoted && ((sb.charAt(i) == '{' && curlyBraceCount >= 1) || (sb.charAt(i) == '{' && sb.charAt(i - 1) == ' ' && curlyBraceCount == 0))) {
                curlyBraceCount++;
            } else if (notQuoted && sb.charAt(i) == '}' && curlyBraceCount > 0) {
                curlyBraceCount--;
                if (curlyBraceCount == 0) {
                    args.add(sb.substring(start, i + 1));
                    start = i + 1;
                }
            }
            if (curlyBraceCount == 0) {
                if (sb.charAt(i) == ' ' && notQuoted) {
                    String arg = sb.substring(start, i);
                    if (!arg.isEmpty()) {
                        args.add(arg);
                    }
                    start = i + 1;
                } else if (sb.charAt(i) == '"') {
                    sb.deleteCharAt(i);
                    --i;
                    notQuoted = !notQuoted;
                }
            }
        }

        String arg = sb.substring(start);
        if (!arg.isEmpty()) {
            args.add(arg);
        }
        return args;
    }

    public static Map<String, String> parseOptions(String preset) {
        if (preset.isBlank()) return Collections.emptyMap();
        var splits = fastSplit(preset, ";");
        var options = new HashMap<String, String>();
        for (var split : splits) {
            if (!split.contains("=")) {
                log.warn("Invalid option: {}", split);
                continue;
            }
            var kv = fastTwoPartSplit(split, "=", "");
            options.put(kv[0], kv[1]);
        }
        return options;
    }

    /**
     * Converts a snake_case string to Title Case with spaces.
     * <p>
     * For example: "aaa_bbb_ccc" -> "Aaa Bbb Ccc"
     *
     * @param snakeCase the snake_case string to convert
     * @return the Title Case string with spaces
     */
    public static String snakeCaseToTitleCase(String snakeCase) {
        if (snakeCase == null || snakeCase.isEmpty()) {
            return snakeCase;
        }
        var parts = fastSplit(snakeCase, "_");
        var sb = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            var part = parts.get(i);
            if (!part.isEmpty()) {
                sb.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    sb.append(part.substring(1).toLowerCase());
                }
            }
            if (i < parts.size() - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
