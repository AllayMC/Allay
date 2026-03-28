package org.allaymc.api.ddui;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Reactive value holder used by DDUI bindings.
 *
 * @param <T> value type
 *
 * @author Miroshka
 * @author xRookieFight
 */
public final class Observable<T> {

    private static final ThreadLocal<Integer> SUPPRESSED_BINDINGS = ThreadLocal.withInitial(() -> 0);

    private final Set<Consumer<T>> listeners = new LinkedHashSet<>();
    private T value;

    /**
     * Creates a new observable with the given initial value.
     *
     * @param value initial value
     */
    public Observable(T value) {
        this.value = value;
    }

    /**
     * Returns the current value.
     *
     * @return current value
     */
    public T getValue() {
        return value;
    }

    /**
     * Updates the value and notifies all subscribers.
     *
     * @param value new value
     */
    public void setValue(T value) {
        this.value = value;
        listeners.forEach(listener -> listener.accept(value));
    }

    /**
     * Registers a listener that will be called whenever the value changes.
     *
     * @param listener value listener
     */
    public void subscribe(Consumer<T> listener) {
        listeners.add(listener);
    }

    /**
     * Removes a previously registered listener.
     *
     * @param listener value listener
     */
    public void unsubscribe(Consumer<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Returns whether DDUI binding propagation is currently suppressed for this thread.
     *
     * @return {@code true} if bindings are suppressed
     */
    public static boolean bindingsSuppressed() {
        return SUPPRESSED_BINDINGS.get() > 0;
    }

    /**
     * Runs the given action with DDUI binding propagation suppressed on the current thread.
     *
     * @param runnable action to execute
     */
    public static void withBindingsSuppressed(Runnable runnable) {
        var depth = SUPPRESSED_BINDINGS.get();
        SUPPRESSED_BINDINGS.set(depth + 1);
        try {
            runnable.run();
        } finally {
            if (depth == 0) {
                SUPPRESSED_BINDINGS.remove();
            } else {
                SUPPRESSED_BINDINGS.set(depth);
            }
        }
    }
}
