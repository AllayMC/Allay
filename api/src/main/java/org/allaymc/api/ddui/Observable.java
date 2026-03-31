package org.allaymc.api.ddui;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * Reactive value source used by DDUI properties.
 * <p>
 * An observable can drive live updates for a screen or element property after the screen has been shown.
 * For editable controls, a client-writable observable may also receive values originating from the client.
 *
 * @param <T> the value type
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Observable<T> {
    private final Set<Consumer<T>> listeners = new CopyOnWriteArraySet<>();
    private final boolean clientWritable;
    private T value;

    private Observable(T value, boolean clientWritable) {
        this.value = value;
        this.clientWritable = clientWritable;
    }

    /**
     * Creates an observable whose value is only written by server-side code.
     *
     * @param initialValue the initial value
     * @param <T>          the value type
     * @return the created observable
     */
    public static <T> Observable<T> create(T initialValue) {
        return create(initialValue, false);
    }

    /**
     * Creates an observable with an explicit client-write policy.
     *
     * @param initialValue   the initial value
     * @param clientWritable whether client-originated DDUI updates may write back into this observable
     * @param <T>            the value type
     * @return the created observable
     */
    public static <T> Observable<T> create(T initialValue, boolean clientWritable) {
        return new Observable<>(initialValue, clientWritable);
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public T get() {
        return value;
    }

    /**
     * Updates the current value and notifies all subscribers.
     *
     * @param value the new value
     */
    public void set(T value) {
        this.value = value;
        for (var listener : listeners) {
            listener.accept(value);
        }
    }

    /**
     * Checks whether client-originated DDUI updates may write back into this observable.
     * <p>
     * This flag is only relevant when the observable is bound to an editable DDUI property.
     *
     * @return {@code true} if the client may write into this observable
     */
    public boolean clientWritable() {
        return clientWritable;
    }

    /**
     * Subscribes to future value changes.
     * <p>
     * The listener is not invoked immediately with the current value.
     *
     * @param listener the listener to add
     * @return a handle that unsubscribes the listener when closed
     */
    public AutoCloseable subscribe(Consumer<T> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }
}
