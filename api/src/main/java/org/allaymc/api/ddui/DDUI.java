package org.allaymc.api.ddui;

/**
 * Factory entry point for creating DDUI screens and observables in Allay.
 *
 * @author Miroshka
 * @author xRookieFight
 */
public final class DDUI {

    private DDUI() {
    }

    /**
     * Creates a new DDUI custom form screen.
     *
     * @return custom form screen builder
     */
    public static CustomFormScreen customForm() {
        return new CustomFormScreen();
    }

    /**
     * Creates a new DDUI message box screen.
     *
     * @return message box screen builder
     */
    public static MessageBoxScreen messageBox() {
        return new MessageBoxScreen();
    }

    /**
     * Creates a new observable value that can be bound to DDUI properties.
     *
     * @param value initial value
     * @param <T> value type
     *
     * @return observable wrapper
     */
    public static <T> Observable<T> observable(T value) {
        return new Observable<>(value);
    }
}
