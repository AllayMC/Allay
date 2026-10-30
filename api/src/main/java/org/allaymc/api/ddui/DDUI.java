package org.allaymc.api.ddui;

import org.allaymc.api.ddui.type.CustomFormScreen;
import org.allaymc.api.ddui.type.MessageBoxScreen;

/**
 * {@code DDUI} creates DDUI screen templates and observable property values. Callers configure a
 * template and send it to a {@link DDUIViewer}; an observable can then supply property updates to
 * the runtime sessions created from that template.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUI {
    /**
     * Creates a new custom-form screen template with no elements.
     */
    static CustomFormScreen customForm() {
        return new CustomFormScreen();
    }

    /**
     * Creates a new message-box screen template with default button labels.
     */
    static MessageBoxScreen messageBox() {
        return new MessageBoxScreen();
    }

    /**
     * Creates an observable initialized with the supplied value and server-only writes. Editable
     * DDUI controls can still maintain session values, but client updates do not write into this
     * observable.
     *
     * @param <T> stored value type
     */
    static <T> Observable<T> observable(T initialValue) {
        return Observable.create(initialValue);
    }

    /**
     * Creates an observable with the supplied value and client-write policy. The policy applies
     * when an editable DDUI property binds this observable; server code can call {@code set} under
     * either policy.
     *
     * @param <T> stored value type
     */
    static <T> Observable<T> observable(T initialValue, boolean clientWritable) {
        return Observable.create(initialValue, clientWritable);
    }
}
