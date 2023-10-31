package cn.allay.api.command.exception;

import cn.allay.api.utils.StringUtils;
import lombok.Getter;

@Getter
public class ArgumentParseException extends RuntimeException {

    private final String source;
    private final int position;

    /**
     * Return a new {@link ArgumentParseException} with the given message, source and position.
     *
     * @param message  The message to use for this exception
     * @param source   The source string being parsed
     * @param position The current position in the source string
     */
    public ArgumentParseException(String message, String source, int position) {
        super(message);
        this.source = source;
        this.position = position;
    }

    /**
     * Return a new {@link ArgumentParseException} with the given message, cause, source and position.
     *
     * @param message  The message to use for this exception
     * @param cause    The cause for this exception
     * @param source   The source string being parsed
     * @param position The current position in the source string
     */
    public ArgumentParseException(String message, Throwable cause, String source, int position) {
        super(message, cause);
        this.source = source;
        this.position = position;
    }

    /**
     * Return a string pointing to the position of the arguments when this
     * exception occurs.
     *
     * @return The appropriate position string
     */
    public String getAnnotatedPosition() {
        String source = this.source;
        int position = this.position;
        if (source.length() > 80 && position >= 37) {
            position -= 40;
        }

        return StringUtils.repeat(" ", position) + "^";
    }
}
