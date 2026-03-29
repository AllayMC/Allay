package org.allaymc.api.ddui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Reactive value holder used by DDUI bindings.
 *
 * @param <T> value type
 *
 * @author Miroshka
 */
public final class Observable<T> {

    /**
     * Disposable handle for a DDUI observable subscription.
     *
     * @author Miroshka
     */
    @FunctionalInterface
    public interface Subscription {
        void cancel();
    }

    private final Map<Object, Consumer<T>> listeners = new LinkedHashMap<>();
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
        if (!BindingScope.isSuppressed()) {
            listeners.values().forEach(listener -> listener.accept(value));
        }
    }

    public Subscription bind(Consumer<T> listener) {
        var key = new Object();
        listeners.put(key, listener);
        return () -> listeners.remove(key);
    }

    public void subscribe(Consumer<T> listener) {
        listeners.put(listener, listener);
    }

    public static boolean bindingsSuppressed() {
        return BindingScope.isSuppressed();
    }

    public static void withBindingsSuppressed(Runnable runnable) {
        BindingScope.suppressed(runnable);
    }

    public void unsubscribe(Consumer<T> listener) {
        listeners.remove(listener);
    }
}
