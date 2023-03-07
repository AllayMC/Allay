package cn.allay.nbt;

/**
 * An exception thrown when an error occurs while created a tag instance.
 */
public class TagCreateException extends Exception {
    public TagCreateException() {
        super();
    }

    public TagCreateException(String message) {
        super(message);
    }

    public TagCreateException(Throwable cause) {
        super(cause);
    }

    public TagCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}