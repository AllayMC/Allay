package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * {@code AllayStringUtils} splits identifier and option text and groups command arguments
 * while preserving quoted or brace-delimited content. The fast list splitter consumes one
 * delimiter character per match, while the two-part splitter consumes the full delimiter and
 * retains its default first component when the delimiter starts the input.
 *
 * <p>
 * Command grouping removes quote delimiters outside brace groups, but preserves quotes inside
 * a brace group so JSON-like command data keeps its internal syntax. Brace depth prevents
 * spaces and nested objects from separating arguments before the matching closing brace.
 * This parser performs no escape decoding or balanced-delimiter validation, and its preceding
 * character check means an opening brace at index zero can throw instead of starting a group.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class AllayStringUtils {
    /**
     * Splits text into a mutable list using the effectively unbounded split limit. Callers use
     * a nonempty single-character delimiter because the implementation advances only one character
     * after each match, even for longer delimiter strings.
     */
    public static List<String> fastSplit(String str, String delimiter) {
        return fastSplit(str, delimiter, Integer.MAX_VALUE);
    }

    /**
     * Splits text into at most {@code limit} parts and retains the unsplit suffix in the final
     * part. Empty parts are retained, the limit must exceed one, and callers use a
     * single-character
     * delimiter because each match consumes only one character.
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
     * Splits at the first complete delimiter into a new two-element array. A missing delimiter
     * returns {@code defaultPartOne} and the original input, while a delimiter at index zero
     * keeps that default and removes the leading delimiter from the second part.
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

    /**
     * Groups space-delimited command arguments while preserving quoted content and nested brace
     * groups. Quotes outside braces are removed from the copied text, while quotes inside braces
     * remain part of the argument so JSON-like data retains its representation.
     *
     * <p>
     * An opening brace after a space starts a group, and nested braces keep spaces from ending
     * that argument until its closing brace. The parser does not reject unmatched quotes or
     * braces, does not interpret escapes, and can fail when an opening brace appears at index
     * zero because its preceding-character check has no lower-bound guard.
     */
    public static LinkedList<String> splitCommandArgs(String cmdLine) {
        StringBuilder sb = new StringBuilder(cmdLine);
        LinkedList<String> args = new LinkedList<>();
        boolean notQuoted = true;
        int curlyBraceCount = 0;
        int start = 0;

        // Brace groups retain their internal quotes and spaces, while outer quote markers
        // only control token boundaries. Keep both states separate so JSON syntax survives grouping.
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

    /**
     * Parses semicolon-separated key/value options without trimming their text. Blank input
     * returns
     * an immutable empty map; other input returns a mutable map, skips segments without
     * {@code "="} after warning, and keeps the last value for repeated keys.
     */
    public static Map<String, String> parseOptions(String preset) {
        if (preset.isBlank()) return Collections.emptyMap();
        var splits = fastSplit(preset, ";");
        var options = new HashMap<String, String>();
        for (var split : splits) {
            if (!split.contains("=")) {
                // A segment without an equals sign has no value boundary. Skip that segment
                // while retaining other parsed options instead of inventing a key/value association.
                log.warn("Invalid option: {}", split);
                continue;
            }
            var kv = fastTwoPartSplit(split, "=", "");
            options.put(kv[0], kv[1]);
        }
        return options;
    }

    /**
     * Converts underscore-separated words to title case with spaces. {@code null} and empty input
     * are returned unchanged, empty segments still contribute separating spaces, and remaining
     * letters use {@code Locale.ROOT} lowercase after the leading character is uppercased.
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
                    sb.append(part.substring(1).toLowerCase(Locale.ROOT));
                }
            }
            if (i < parts.size() - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
