package org.allaymc.api.command.selector;

import lombok.experimental.StandardException;
import org.allaymc.api.utils.TextFormat;

/**
 * {@code SelectorSyntaxException} reports malformed selector syntax or a
 * failure while interpreting a selector argument. The server wraps handler
 * failures with the argument key, and {@code getMessage} includes the causal
 * chain so command feedback can identify the underlying parse failure.
 */
@StandardException
public class SelectorSyntaxException extends Exception {

    /**
     * Returns the base message followed by red diagnostic lines for each cause.
     * The base message must be non-null because the builder is initialized from
     * it; each cause contributes its simple class name and message.
     */
    @Override
    public String getMessage() {
        var builder = new StringBuilder(super.getMessage());
        Throwable t = this;
        while (t.getCause() != null) {
            t = t.getCause();
            builder.append("\n");
            builder.append(TextFormat.RED).append("Caused by ");
            builder.append(t.getClass().getSimpleName());
            builder.append(": ");
            builder.append(t.getMessage());
        }
        return builder.toString();
    }
}
