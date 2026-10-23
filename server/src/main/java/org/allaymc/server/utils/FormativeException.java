package org.allaymc.server.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * {@code FormativeException} represents a runtime error with a message assembled using
 * brace-placeholder formatting. Constructors can retain a supplied throwable for inclusion
 * in the rendered text, but they do not install it as the inherited exception cause.
 * The retained throwable is transient, while the completed message remains ordinary
 * serializable exception state.
 *
 * <p>
 * Placeholder scanning stores substitution positions and an escape marker for later formatting.
 * An unused final throwable is recognized only when the scanner's reported count is smaller
 * than the supplied argument count. Escaped patterns select the slower formatter because cached
 * positions alone cannot reproduce escape handling; the message is then fixed for subsequent
 * {@code getMessage} and {@code toString} calls.
 *
 * @author duanxinyuan
 */
public class FormativeException extends RuntimeException {

    // indices and usedCount retain the scan result consumed by formatMessage. The first
    // index is negative when escape handling requires the scanning formatter instead of cached positions.
    /**
     * Cached placeholder scan positions.
     */
    private int[] indices;
    /**
     * Arguments selected for substitution.
     */
    private int usedCount;
    /**
     * Completed formatted message text.
     */
    private String message;
    /**
     * {@code throwable} retains an error whose stack trace can be appended to {@code message}.
     * It is transient and is not assigned as the inherited exception cause.
     */
    private transient Throwable throwable;

    /**
     * Creates an exception whose custom message initially remains {@code null}.
     */
    public FormativeException() {
        super();
    }

    /**
     * Creates an exception retaining the supplied message without placeholder substitution.
     */
    public FormativeException(String message) {
        this.message = message;
    }

    /**
     * Creates an exception whose message is the supplied throwable's stack trace. The throwable is
     * retained in a separate transient field rather than the inherited cause slot, so
     * {@code getCause} does not expose it.
     */
    public FormativeException(Throwable cause) {
        this.throwable = cause;
        this.message = ExceptionUtils.getStackTrace(throwable);
    }

    /**
     * Creates an exception with a substituted message and an optional trailing throwable trace.
     * A final throwable is retained only when the placeholder scanner reports fewer used positions
     * than the argument array supplies; a throwable consumed as a formatting argument is not
     * automatically the inherited cause.
     *
     * <p>
     * The scanner selects cached-position formatting for patterns without escapes and scanning
     * formatting otherwise. Formatting occurs during construction, and the resulting message no
     * longer depends on subsequent changes to the argument array.
     */
    public FormativeException(String format, Object... arguments) {
        init(format, arguments);
        fillInStackTrace();
        this.message = formatMessage(format, arguments);
        if (throwable != null) {
            this.message += System.lineSeparator() + ExceptionUtils.getStackTrace(throwable);
        }
    }

    /**
     * Returns the custom message text assembled by this exception's constructor.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Returns the exception class name followed by its custom message when present. A
     * {@code null} message yields only the class name, and the separately retained throwable is
     * not rendered again by this method.
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

    /**
     * Scans the pattern to prepare placeholder positions and the substitution count. The index
     * array has at least one slot because the scanner uses its first element as an escape marker.
     *
     * <p>
     * The scanner's count also determines whether a final throwable lies beyond the consumed
     * arguments. {@code usedCount} is capped by the available argument count before formatting,
     * and a negative first index forces escape-aware scanning rather than direct use of the
     * cached substitution positions.
     */
    private void init(String format, Object... arguments) {
        // A placeholder needs two characters, but keep one slot even for an empty pattern
        // because the scanner and formatter inspect indices[0] to select escape handling.
        final int len = Math.max(1, format == null ? 0 : format.length() >> 1);
        this.indices = new int[len]; // LOG4J2-1542 requires a nonzero marker slot.
        final int placeholders = ParameterFormatter.countArgumentPlaceholders2(format, indices);
        initThrowable(arguments, placeholders);
        this.usedCount = Math.min(placeholders, arguments == null ? 0 : arguments.length);
    }

    /**
     * Retains a final throwable only when it lies beyond the scanner's reported used-parameter
     * count. Existing retained throwables are not replaced, and the selected value is stored
     * separately from the inherited cause.
     */
    private void initThrowable(final Object[] params, final int usedParams) {
        if (params != null) {
            final int argCount = params.length;
            if (usedParams < argCount && this.throwable == null && params[argCount - 1] instanceof Throwable) {
                this.throwable = (Throwable) params[argCount - 1];
            }
        }
    }

    /**
     * Formats arguments using the scan result prepared by {@code init}. A negative first index
     * selects escape-aware scanning, while other patterns use cached positions; the returned
     * string becomes the constructor's stored message.
     */
    private String formatMessage(String format, Object... arguments) {
        StringBuilder stringBuilder = new StringBuilder();
        // Escape characters invalidate the cached-position fast path because substitution
        // must also reproduce literal delimiters and backslashes, not merely copy pattern spans.
        if (indices[0] < 0) {
            ParameterFormatter.formatMessage(stringBuilder, format, arguments, usedCount);
        } else {
            ParameterFormatter.formatMessage2(stringBuilder, format, arguments, usedCount, indices);
        }
        return stringBuilder.toString();
    }
}
