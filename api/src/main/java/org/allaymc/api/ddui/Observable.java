package org.allaymc.api.ddui;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * {@code Observable} stores a reactive DDUI value and notifies subscribed consumers when server
 * code replaces it. The client-write policy controls whether editable session bindings may also
 * write values back into this shared source.
 *
 * <p>
 * {@code set} stores the new value before invoking listeners synchronously on the calling thread. A
 * listener exception stops the remaining notifications while leaving the new value stored.
 * {@code listeners} supports subscription changes during traversal, but {@code value} and compound
 * session operations have no synchronization here; callers must provide the coordination needed by
 * their use of shared observables.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Observable<T> {
    /**
     * {@code listeners} holds subscribed callbacks. A notification traverses a snapshot, so
     * subscription changes do not alter that traversal.
     */
    private final Set<Consumer<T>> listeners = new CopyOnWriteArraySet<>();
    /**
     * Whether editable bindings permit client writes.
     */
    private final boolean clientWritable;
    /**
     * {@code value} holds the current shared reference, including {@code null}; {@code set} stores
     * it before notifying listeners.
     */
    private T value;

    /**
     * Creates a reactive value with the supplied client-write policy.
     */
    private Observable(T value, boolean clientWritable) {
        this.value = value;
        this.clientWritable = clientWritable;
    }

    /**
     * Creates an observable holding the supplied reference with client writes disabled. Server code
     * can replace the value, while DDUI client updates leave this source unchanged unless client
     * writes are explicitly enabled.
     */
    public static <T> Observable<T> create(T initialValue) {
        return create(initialValue, false);
    }

    /**
     * Creates an observable holding the supplied reference and client-write policy. No value copy
     * is made, and server-side {@code set} remains available regardless of the policy selected for
     * client updates.
     */
    public static <T> Observable<T> create(T initialValue, boolean clientWritable) {
        return new Observable<>(initialValue, clientWritable);
    }

    /**
     * Returns the current stored reference without copying its contents.
     */
    public T get() {
        return value;
    }

    /**
     * Stores the supplied reference and synchronously notifies a snapshot of subscribers. Equal
     * values still notify; a listener exception propagates and prevents later listeners in that
     * traversal from receiving the notification.
     */
    public void set(T value) {
        this.value = value;
        for (var listener : listeners) {
            listener.accept(value);
        }
    }

    /**
     * Returns whether editable DDUI bindings may write client values into this source. The flag
     * does not restrict direct server-side calls to {@code set}, and unbound observables have no
     * client update path.
     */
    public boolean clientWritable() {
        return clientWritable;
    }

    /**
     * Adds a listener for future synchronous value notifications without sending the current value.
     * The returned handle removes that listener when closed; duplicate subscriptions share set
     * membership rather than independent registrations.
     */
    public AutoCloseable subscribe(Consumer<T> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }
}
