package org.allaymc.api.command.selector;

import lombok.experimental.StandardException;
import org.allaymc.api.utils.TextFormat;

@StandardException
public class SelectorSyntaxException extends Exception {

    @Override
    public String getMessage() {
        var builder = new StringBuilder(super.getMessage());
        Throwable t = this;
        while (t.getCause() != null) {
            // Reached the bottom level
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
