package org.allaymc.api.ddui;

import org.allaymc.api.ddui.type.CustomFormScreen;
import org.allaymc.api.ddui.type.MessageBoxScreen;

/**
 * Factory methods for data-driven UI screens and observables.
 *
 * @author OpenAI
 */
public interface DDUI {
    /**
     * Creates a custom-form DDUI screen.
     *
     * @return the created screen
     */
    static CustomFormScreen customForm() {
        return new CustomFormScreen();
    }

    /**
     * Creates a message-box DDUI screen.
     *
     * @return the created screen
     */
    static MessageBoxScreen messageBox() {
        return new MessageBoxScreen();
    }

    /**
     * Creates a server-writable observable.
     *
     * @param initialValue the initial value
     * @param <T>          the value type
     * @return the created observable
     */
    static <T> Observable<T> observable(T initialValue) {
        return Observable.create(initialValue);
    }

    /**
     * Creates an observable with an explicit client-writable flag.
     *
     * @param initialValue   the initial value
     * @param clientWritable whether client-originated DDUI updates may write back into this observable
     * @param <T>            the value type
     * @return the created observable
     */
    static <T> Observable<T> observable(T initialValue, boolean clientWritable) {
        return Observable.create(initialValue, clientWritable);
    }
}
