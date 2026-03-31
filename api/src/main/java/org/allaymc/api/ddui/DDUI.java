package org.allaymc.api.ddui;

import org.allaymc.api.ddui.type.CustomFormScreen;
import org.allaymc.api.ddui.type.MessageBoxScreen;

/**
 * Entry-point factory for DDUI screen templates and reactive values.
 * <p>
 * The helpers in this type mirror the fluent style of the form API:
 * create a screen template, configure its properties and elements, then send it to a {@link DDUIViewer}.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUI {
    /**
     * Creates a custom-form DDUI screen template.
     *
     * @return the created screen
     */
    static CustomFormScreen customForm() {
        return new CustomFormScreen();
    }

    /**
     * Creates a message-box DDUI screen template.
     *
     * @return the created screen
     */
    static MessageBoxScreen messageBox() {
        return new MessageBoxScreen();
    }

    /**
     * Creates an observable whose value is owned by the server.
     * <p>
     * Client-originated DDUI updates do not write back into this observable unless
     * {@link #observable(Object, boolean)} is used with {@code clientWritable} set to {@code true}.
     *
     * @param initialValue the initial value
     * @param <T>          the value type
     * @return the created observable
     */
    static <T> Observable<T> observable(T initialValue) {
        return Observable.create(initialValue);
    }

    /**
     * Creates an observable with an explicit client-write policy.
     * <p>
     * When a client-writable observable is bound to an editable DDUI property, client-side interactions may
     * update the observable directly.
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
