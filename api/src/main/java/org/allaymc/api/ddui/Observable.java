package org.allaymc.api.ddui;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * A reactive value used by DDUI screens.
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
     * Creates a server-writable observable.
     *
     * @param initialValue the initial value
     * @param <T>          the value type
     * @return the created observable
     */
    public static <T> Observable<T> create(T initialValue) {
        return create(initialValue, false);
    }

    /**
     * Creates an observable with an explicit client-writable flag.
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
     * Updates the current value and notifies subscribers.
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
     *
     * @return {@code true} if the client may write into this observable
     */
    public boolean clientWritable() {
        return clientWritable;
    }

    /**
     * Subscribes to value changes.
     *
     * @param listener the listener to add
     * @return a handle that unsubscribes the listener when closed
     */
    public AutoCloseable subscribe(Consumer<T> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }
}
