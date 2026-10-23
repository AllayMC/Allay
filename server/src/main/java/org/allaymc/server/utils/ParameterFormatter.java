package org.allaymc.server.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * {@code ParameterFormatter} substitutes brace placeholders and renders diagnostic argument
 * values for formatted server exceptions. Escaping determines whether a delimiter consumes
 * an argument, and indexed formatting is available when the caller has prepared valid
 * placeholder positions for an escape-free pattern. The scanner used by formatted exceptions
 * also stores an escape marker to select the scanning formatter.
 *
 * <p>
 * Deep rendering tracks container identities along each recursion path so self-reference can
 * be shown without recursively expanding the same ancestor forever. Child paths receive
 * copies of the visited set, allowing a shared object in separate sibling positions to be
 * rendered at both positions. Identity strings use identity hash codes and can collide;
 * container iteration or specialized scalar conversion can still throw, while ordinary
 * object {@code toString} failures receive an explicit diagnostic marker.
 */
final class ParameterFormatter {
    // RECURSION_PREFIX and RECURSION_SUFFIX surround the identity of a container already
    // present on the current rendering path, identifying recursion without expanding that ancestor again.
    /**
     * Opening recursion diagnostic marker.
     */
    static final String RECURSION_PREFIX = "[...";
    /**
     * Closing recursion diagnostic marker.
     */
    static final String RECURSION_SUFFIX = "...]";

    // ERROR_PREFIX, ERROR_SEPARATOR, ERROR_MSG_SEPARATOR, and ERROR_SUFFIX delimit the
    // object identity and thrown error text when an ordinary object's toString cannot be rendered.
    /**
     * Opening rendering-failure marker.
     */
    static final String ERROR_PREFIX = "[!!!";
    /**
     * Object-to-error identity separator.
     */
    static final String ERROR_SEPARATOR = "=>";
    /**
     * Exception-to-message text separator.
     */
    static final String ERROR_MSG_SEPARATOR = ":";
    /**
     * Closing rendering-failure marker.
     */
    static final String ERROR_SUFFIX = "!!!]";

    // DELIM_START and DELIM_STOP identify placeholder pairs, while ESCAPE_CHAR controls
    // whether those pairs remain literal or consume arguments in the scanning formatter.
    /**
     * Opening placeholder delimiter character.
     */
    private static final char DELIM_START = '{';
    /**
     * Closing placeholder delimiter character.
     */
    private static final char DELIM_STOP = '}';
    /**
     * Placeholder escape marker character.
     */
    private static final char ESCAPE_CHAR = '\\';

    /**
     * {@code dateTimeFormatter} renders date values with millisecond precision and a numeric zone
     * offset. {@code appendDate} supplies the system-default zone at each formatting call.
     */
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * Prevents construction of the stateless placeholder and diagnostic argument formatter.
     */
    private ParameterFormatter() {
    }

    /**
     * Counts unescaped delimiter pairs, returning zero for a {@code null} pattern. Backslash parity
     * controls whether a pair consumes an argument; this operation neither stores positions nor
     * rewrites escape characters in the input.
     */
    static int countArgumentPlaceholders(final String messagePattern) {
        if (messagePattern == null) {
            return 0;
        }
        final int length = messagePattern.length();
        int result = 0;
        boolean isEscaped = false;
        for (int i = 0; i < length - 1; i++) {
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                isEscaped = !isEscaped;
            } else if (curChar == DELIM_START) {
                if (!isEscaped && messagePattern.charAt(i + 1) == DELIM_STOP) {
                    result++;
                    i++;
                }
                isEscaped = false;
            } else {
                isEscaped = false;
            }
        }
        return result;
    }

    /**
     * Scans delimiter positions and marks {@code indices[0]} negative after a scanned escape.
     * A {@code null} pattern returns zero without touching the array, and callers provide storage for
     * every indexed position the scan can write.
     *
     * <p>
     * The reported count increases for each scanned backslash as well as each unescaped pair,
     * so it is not simply the number of substitutions when escapes occur. Later position writes
     * use that increased count and can exceed a placeholder-only allocation; formatted exceptions
     * also use the count to decide whether a final throwable is unused.
     */
    static int countArgumentPlaceholders2(final String messagePattern, final int[] indices) {
        if (messagePattern == null) {
            return 0;
        }
        final int length = messagePattern.length();
        int result = 0;
        boolean isEscaped = false;
        for (int i = 0; i < length - 1; i++) {
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                isEscaped = !isEscaped;
                // Escape handling changes both literal text and argument consumption. Mark
                // cached positions unusable so the caller selects the escape-aware scanning formatter.
                indices[0] = -1;
                result++;
            } else if (curChar == DELIM_START) {
                if (!isEscaped && messagePattern.charAt(i + 1) == DELIM_STOP) {
                    indices[result] = i;
                    result++;
                    i++;
                }
                isEscaped = false;
            } else {
                isEscaped = false;
            }
        }
        return result;
    }

    /**
     * Counts unescaped pairs within the supplied array prefix and stores their positions.
     * Callers provide a valid prefix length and enough index storage; this variant does not set
     * an escape marker, so its positions alone do not reproduce escape rewriting.
     */
    static int countArgumentPlaceholders3(final char[] messagePattern, final int length, final int[] indices) {
        int result = 0;
        boolean isEscaped = false;
        for (int i = 0; i < length - 1; i++) {
            final char curChar = messagePattern[i];
            if (curChar == ESCAPE_CHAR) {
                isEscaped = !isEscaped;
            } else if (curChar == DELIM_START) {
                if (!isEscaped && messagePattern[i + 1] == DELIM_STOP) {
                    indices[result] = i;
                    result++;
                    i++;
                }
                isEscaped = false;
            } else {
                isEscaped = false;
            }
        }
        return result;
    }

    /**
     * Formats a pattern with all supplied arguments through the scanning formatter. Missing
     * arguments leave delimiter pairs visible, a {@code null} argument array performs no substitution,
     * and a {@code null} pattern becomes the literal string {@code "null"}.
     */
    static String format(final String messagePattern, final Object[] arguments) {
        final StringBuilder result = new StringBuilder();
        final int argCount = arguments == null ? 0 : arguments.length;
        formatMessage(result, messagePattern, arguments, argCount);
        return result.toString();
    }

    /**
     * Appends substitutions using precomputed positions for an escape-free string pattern.
     * Callers ensure {@code argCount} fits both the argument array and valid sorted positions;
     * this operation does not scan or unescape the copied pattern spans.
     *
     * <p>
     * A {@code null} pattern, {@code null} argument array, or zero count appends the pattern directly, including
     * the literal {@code "null"} for a {@code null} pattern. Remaining pattern text follows the last
     * substituted pair unchanged.
     */
    static void formatMessage2(final StringBuilder buffer, final String messagePattern,
                               final Object[] arguments, final int argCount, final int[] indices) {
        if (messagePattern == null || arguments == null || argCount == 0) {
            buffer.append(messagePattern);
            return;
        }
        int previous = 0;
        for (int i = 0; i < argCount; i++) {
            buffer.append(messagePattern, previous, indices[i]);
            previous = indices[i] + 2;
            recursiveDeepToString(arguments[i], buffer, null);
        }
        buffer.append(messagePattern, previous, messagePattern.length());
    }

    /**
     * Appends character-array slices using the supplied positions and count arguments. The current
     * implementation passes absolute placeholder positions and {@code patternLength} to an append
     * overload whose final argument is a character count, so ordinary substitution can overrun
     * the array or copy beyond the intended prefix even with otherwise valid positions.
     *
     * <p>
     * A {@code null} pattern appends nothing. A {@code null} argument array or zero count appends the entire
     * array rather than just the requested prefix; this method does not provide a reliable
     * prefix-formatting contract for the substitution path.
     */
    static void formatMessage3(final StringBuilder buffer, final char[] messagePattern, final int patternLength,
                               final Object[] arguments, final int argCount, final int[] indices) {
        if (messagePattern == null) {
            return;
        }
        if (arguments == null || argCount == 0) {
            buffer.append(messagePattern);
            return;
        }
        int previous = 0;
        for (int i = 0; i < argCount; i++) {
            buffer.append(messagePattern, previous, indices[i]);
            previous = indices[i] + 2;
            recursiveDeepToString(arguments[i], buffer, null);
        }
        buffer.append(messagePattern, previous, patternLength);
    }

    /**
     * Appends the pattern with argument substitutions while preserving its escape semantics.
     * A {@code null} pattern, {@code null} argument array, or zero argument count appends the pattern unchanged,
     * and callers ensure the requested argument count fits the array.
     *
     * <p>
     * Backslashes before a delimiter pair collapse in pairs. An odd leftover slash makes the
     * delimiter literal without consuming an argument, while an even count permits substitution;
     * slashes before ordinary characters remain literal. Missing arguments keep the delimiter
     * pair visible, and the final-character helper retains trailing text or escapes excluded
     * from the one-character-lookahead scan.
     */
    static void formatMessage(final StringBuilder buffer, final String messagePattern,
                              final Object[] arguments, final int argCount) {
        if (messagePattern == null || arguments == null || argCount == 0) {
            buffer.append(messagePattern);
            return;
        }
        int escapeCounter = 0;
        int currentArgument = 0;
        int i = 0;
        final int len = messagePattern.length();
        // Delimiter recognition reads one character ahead. Leave the last character to
        // the trailing-text helper so a final literal or backslash is neither lost nor overread.
        for (; i < len - 1; i++) {
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                escapeCounter++;
            } else {
                if (isDelimPair(curChar, messagePattern, i)) {
                    i++;

                    // Paired backslashes become literal backslashes before this delimiter.
                    // An odd remaining slash protects the pair and must not advance the argument index.
                    writeEscapedEscapeChars(escapeCounter, buffer);

                    if (isOdd(escapeCounter)) {
                        writeDelimPair(buffer);
                    } else {
                        writeArgOrDelimPair(arguments, argCount, currentArgument, buffer);
                        currentArgument++;
                    }
                } else {
                    handleLiteralChar(buffer, escapeCounter, curChar);
                }
                escapeCounter = 0;
            }
        }
        handleRemainingCharIfAny(messagePattern, len, buffer, escapeCounter, i);
    }

    // LOG4J2-1096
    /**
     * Returns whether the current and following character form a placeholder pair. The caller
     * must leave a following character inside the pattern because this helper performs its
     * lookahead without a separate bounds check.
     */
    private static boolean isDelimPair(final char curChar, final String messagePattern, final int curCharIndex) {
        return curChar == DELIM_START && messagePattern.charAt(curCharIndex + 1) == DELIM_STOP;
    }

    // LOG4J2-1096
    /**
     * Appends the unprocessed final character when the main scan leaves one.
     */
    private static void handleRemainingCharIfAny(final String messagePattern, final int len,
                                                 final StringBuilder buffer, final int escapeCounter, final int i) {
        if (i == len - 1) {
            final char curChar = messagePattern.charAt(i);
            handleLastChar(buffer, escapeCounter, curChar);
        }
    }

    // LOG4J2-1096
    /**
     * Appends the final literal while retaining its accumulated trailing backslashes.
     */
    private static void handleLastChar(final StringBuilder buffer, final int escapeCounter, final char curChar) {
        if (curChar == ESCAPE_CHAR) {
            writeUnescapedEscapeChars(escapeCounter + 1, buffer);
        } else {
            handleLiteralChar(buffer, escapeCounter, curChar);
        }
    }

    // LOG4J2-1096
    /**
     * Appends accumulated literal backslashes followed by the current ordinary character.
     */
    private static void handleLiteralChar(final StringBuilder buffer, final int escapeCounter, final char curChar) {
        writeUnescapedEscapeChars(escapeCounter, buffer);
        buffer.append(curChar);
    }

    // LOG4J2-1096
    /**
     * Appends both placeholder delimiter characters without consuming any formatting argument.
     */
    private static void writeDelimPair(final StringBuilder buffer) {
        buffer.append(DELIM_START);
        buffer.append(DELIM_STOP);
    }

    // LOG4J2-1096
    /**
     * Returns whether the supplied integer has its least significant bit set.
     */
    private static boolean isOdd(final int number) {
        return (number & 1) == 1;
    }

    // LOG4J2-1096
    /**
     * Appends one literal backslash for each pair of accumulated escape characters.
     */
    private static void writeEscapedEscapeChars(final int escapeCounter, final StringBuilder buffer) {
        // Two escapes before a placeholder encode one literal backslash in the output.
        final int escapedEscapes = escapeCounter >> 1;
        writeUnescapedEscapeChars(escapedEscapes, buffer);
    }

    // LOG4J2-1096
    /**
     * Appends the requested positive number of literal backslashes to the buffer.
     */
    private static void writeUnescapedEscapeChars(int escapeCounter, final StringBuilder buffer) {
        while (escapeCounter > 0) {
            buffer.append(ESCAPE_CHAR);
            escapeCounter--;
        }
    }

    // LOG4J2-1096
    /**
     * Renders the selected argument when its index is below {@code argCount}. Missing arguments
     * leave the placeholder delimiters visible, and callers keep {@code argCount} within the
     * actual argument array bounds.
     */
    private static void writeArgOrDelimPair(final Object[] arguments, final int argCount, final int currentArgument,
                                            final StringBuilder buffer) {
        if (currentArgument < argCount) {
            recursiveDeepToString(arguments[currentArgument], buffer, null);
        } else {
            // A pattern can have more placeholders than supplied arguments. Keep its
            // unmatched pair visible so the output retains that portion of the original pattern.
            writeDelimPair(buffer);
        }
    }

    /**
     * Renders a value with specialized scalar and container handling. {@code null} returns {@code null}
     * and strings are returned unchanged; other values use recursive rendering, which detects
     * ancestor containers but does not suppress every iterator or specialized-conversion failure.
     */
    static String deepToString(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof String) {
            return (String) o;
        }
        final StringBuilder str = new StringBuilder();
        final Set<String> dejaVu = new HashSet<>();
        recursiveDeepToString(o, str, dejaVu);
        return str.toString();
    }

    /**
     * Appends a value through scalar, container, or ordinary-object rendering as appropriate.
     * {@code null} becomes the text {@code "null"} here, and the optional visited set tracks ancestor
     * container identities rather than every value encountered in sibling branches.
     */
    private static void recursiveDeepToString(final Object o, final StringBuilder str, final Set<String> dejaVu) {
        if (appendSpecialTypes(o, str)) {
            return;
        }
        if (isMaybeRecursive(o)) {
            appendPotentiallyRecursiveValue(o, str, dejaVu);
        } else {
            tryObjectToString(o, str);
        }
    }

    /**
     * Appends recognized text, boxed primitives, {@code null}, or date values and reports whether handled.
     * Other values return {@code false} for container or ordinary-object handling; specialized
     * append and date-conversion failures still propagate from this path.
     */
    private static boolean appendSpecialTypes(final Object o, final StringBuilder str) {
        if (o == null || o instanceof String) {
            str.append((String) o);
            return true;
        } else if (o instanceof CharSequence) {
            str.append((CharSequence) o);
            return true;
        } else if (o instanceof Integer) { // LOG4J2-1415 unbox auto-boxed primitives to avoid calling toString()
            str.append(((Integer) o).intValue());
            return true;
        } else if (o instanceof Long) {
            str.append(((Long) o).longValue());
            return true;
        } else if (o instanceof Double) {
            str.append(((Double) o).doubleValue());
            return true;
        } else if (o instanceof Boolean) {
            str.append(((Boolean) o).booleanValue());
            return true;
        } else if (o instanceof Character) {
            str.append(((Character) o).charValue());
            return true;
        } else if (o instanceof Short) {
            str.append(((Short) o).shortValue());
            return true;
        } else if (o instanceof Float) {
            str.append(((Float) o).floatValue());
            return true;
        }
        return appendDate(o, str);
    }

    /**
     * Appends a date's instant using the shared timestamp pattern and current system zone.
     * Non-date values return {@code false}; unsupported date conversions or formatting failures
     * propagate rather than entering the ordinary-object error marker path.
     */
    private static boolean appendDate(final Object o, final StringBuilder str) {
        if (!(o instanceof Date date)) {
            return false;
        }
        str.append(dateTimeFormatter.format(date.toInstant().atZone(ZoneId.systemDefault())));
        return true;
    }

    /**
     * Returns whether a present value is an array, map, or collection.
     */
    private static boolean isMaybeRecursive(final Object o) {
        return o.getClass().isArray() || o instanceof Map || o instanceof Collection;
    }

    /**
     * Dispatches a recognized array, map, or collection to its container renderer.
     */
    private static void appendPotentiallyRecursiveValue(final Object o, final StringBuilder str,
                                                        final Set<String> dejaVu) {
        final Class<?> oClass = o.getClass();
        if (oClass.isArray()) {
            appendArray(o, str, dejaVu, oClass);
        } else if (o instanceof Map) {
            appendMap(o, str, dejaVu);
        } else if (o instanceof Collection) {
            appendCollection(o, str, dejaVu);
        }
    }

    /**
     * Appends primitive arrays with their matching {@code Arrays.toString} overload and
     * recursively
     * renders object arrays. Object arrays use an ancestor identity set so an element referring
     * back to the array produces a recursion marker instead of expanding that ancestor again.
     *
     * <p>
     * Each element receives a copy of the current path set, allowing one shared child array to
     * appear in multiple sibling positions without being mistaken for an ancestor cycle. Identity
     * hash collisions can still misclassify objects, and the source array is read directly rather
     * than copied before rendering.
     */
    private static void appendArray(final Object o, final StringBuilder str, Set<String> dejaVu,
                                    final Class<?> oClass) {
        if (oClass == byte[].class) {
            str.append(Arrays.toString((byte[]) o));
        } else if (oClass == short[].class) {
            str.append(Arrays.toString((short[]) o));
        } else if (oClass == int[].class) {
            str.append(Arrays.toString((int[]) o));
        } else if (oClass == long[].class) {
            str.append(Arrays.toString((long[]) o));
        } else if (oClass == float[].class) {
            str.append(Arrays.toString((float[]) o));
        } else if (oClass == double[].class) {
            str.append(Arrays.toString((double[]) o));
        } else if (oClass == boolean[].class) {
            str.append(Arrays.toString((boolean[]) o));
        } else if (oClass == char[].class) {
            str.append(Arrays.toString((char[]) o));
        } else {
            if (dejaVu == null) {
                dejaVu = new HashSet<>();
            }
            // Track this array as an ancestor, then copy that path for each element.
            // A repeated sibling reference can expand normally while a back-reference receives a marker.
            final String id = identityToString(o);
            if (dejaVu.contains(id)) {
                str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
            } else {
                dejaVu.add(id);
                final Object[] oArray = (Object[]) o;
                str.append('[');
                boolean first = true;
                for (final Object current : oArray) {
                    if (first) {
                        first = false;
                    } else {
                        str.append(", ");
                    }
                    recursiveDeepToString(current, str, new HashSet<>(dejaVu));
                }
                str.append(']');
            }
        }
    }

    /**
     * Appends map entries in their iteration order with recursive key and value rendering.
     * The map's identity joins the ancestor path before iteration, and a map already on that
     * path is represented by a recursion marker.
     *
     * <p>
     * Keys and values each receive separate path copies, so visiting a key does not incorrectly
     * make the same object look cyclic when it appears as a value. Repeated siblings can render
     * again, while ancestor references stop expanding. The map is not snapshotted, and iteration
     * failures or concurrent mutation errors still propagate outside ordinary-object error
     * handling.
     */
    private static void appendMap(final Object o, final StringBuilder str, Set<String> dejaVu) {
        // Map keys and values can refer to the same objects independently. Copy the
        // ancestor path for each branch so only references back into that path indicate recursion.
        if (dejaVu == null) {
            dejaVu = new HashSet<>();
        }
        final String id = identityToString(o);
        if (dejaVu.contains(id)) {
            str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
        } else {
            dejaVu.add(id);
            final Map<?, ?> oMap = (Map<?, ?>) o;
            str.append('{');
            boolean isFirst = true;
            for (final Object o1 : oMap.entrySet()) {
                final Map.Entry<?, ?> current = (Map.Entry<?, ?>) o1;
                if (isFirst) {
                    isFirst = false;
                } else {
                    str.append(", ");
                }
                final Object key = current.getKey();
                final Object value = current.getValue();
                recursiveDeepToString(key, str, new HashSet<>(dejaVu));
                str.append('=');
                recursiveDeepToString(value, str, new HashSet<>(dejaVu));
            }
            str.append('}');
        }
    }

    /**
     * Appends collection elements in iteration order while tracking the collection's ancestor
     * identity. A collection already on the current path produces a recursion marker instead
     * of recursively iterating the same ancestor.
     *
     * <p>
     * Each child receives a fresh copy of that path, so a shared element in two sibling positions
     * is expanded independently rather than automatically treated as a cycle. Identity hash
     * collisions remain possible, and iteration uses the live collection without synchronization
     * or a snapshot; iterator failures therefore still propagate to the formatting caller.
     */
    private static void appendCollection(final Object o, final StringBuilder str, Set<String> dejaVu) {
        // Keep each element's ancestor path independent. Sharing one visited set across
        // siblings would mistake repeated collection values for recursion even without an ancestor cycle.
        if (dejaVu == null) {
            dejaVu = new HashSet<>();
        }
        final String id = identityToString(o);
        if (dejaVu.contains(id)) {
            str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
        } else {
            dejaVu.add(id);
            final Collection<?> oCol = (Collection<?>) o;
            str.append('[');
            boolean isFirst = true;
            for (final Object anOCol : oCol) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    str.append(", ");
                }
                recursiveDeepToString(anOCol, str, new HashSet<>(dejaVu));
            }
            str.append(']');
        }
    }

    /**
     * Appends an ordinary object's {@code toString} result, converting thrown failures into a
     * diagnostic marker. The guard covers that object conversion only; failures while rendering
     * the diagnostic error itself can still propagate.
     */
    private static void tryObjectToString(final Object o, final StringBuilder str) {
        try {
            str.append(o.toString());
        } catch (final Throwable t) {
            // A user object's toString can fail during exception formatting. Preserve its
            // identity and error information without invoking the same broken conversion again.
            handleErrorInObjectToString(o, str, t);
        }
    }

    /**
     * Appends the failed object's identity and the thrown error's class and message. A message
     * equal to the error class name is omitted to avoid duplicate text; this helper does not
     * catch failures retrieving that diagnostic message.
     */
    private static void handleErrorInObjectToString(final Object o, final StringBuilder str, final Throwable t) {
        str.append(ERROR_PREFIX);
        str.append(identityToString(o));
        str.append(ERROR_SEPARATOR);
        final String msg = t.getMessage();
        final String className = t.getClass().getName();
        str.append(className);
        if (!className.equals(msg)) {
            str.append(ERROR_MSG_SEPARATOR);
            str.append(msg);
        }
        str.append(ERROR_SUFFIX);
    }

    /**
     * Returns a class-name and identity-hash string without invoking the object's override.
     * {@code null} returns {@code null}; identity hash collisions remain possible, so the result is useful
     * for diagnostic cycle markers rather than a guaranteed unique object identifier.
     */
    static String identityToString(final Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }

}
