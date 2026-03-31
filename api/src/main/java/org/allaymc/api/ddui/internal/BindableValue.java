package org.allaymc.api.ddui.internal;

import org.allaymc.api.ddui.Observable;
import org.jetbrains.annotations.ApiStatus;

/**
 * Internal holder for a DDUI property that may either be a literal value or an observable binding.
 *
 * @param <T> the property value type
 *
 * @author daoge_cmd | SerenityJS
 */
@ApiStatus.Internal
public final class BindableValue<T> {
    private T value;
    private Observable<T> observable;

    /**
     * Creates a bindable value with an initial literal value.
     *
     * @param value the initial value
     */
    public BindableValue(T value) {
        this.value = value;
    }

    /**
     * Gets the current literal value.
     *
     * @return the current value
     */
    public T value() {
        return value;
    }

    /**
     * Gets the observable backing this value.
     *
     * @return the observable, or {@code null} if this value is literal-only
     */
    public Observable<T> observable() {
        return observable;
    }

    /**
     * Sets a new literal value and clears any observable binding.
     *
     * @param value the new literal value
     */
    public void set(T value) {
        this.value = value;
        this.observable = null;
    }

    /**
     * Binds this value to an observable and snapshots its current value.
     *
     * @param observable the observable binding
     */
    public void bind(Observable<T> observable) {
        this.value = observable.get();
        this.observable = observable;
    }
}
