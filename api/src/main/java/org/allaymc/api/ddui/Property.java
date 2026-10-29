package org.allaymc.api.ddui;

/**
 * Configured property value of a DDUI screen or element.
 * <p>
 * A property either stores a literal value or points at an {@link Observable} that supplies runtime updates after
 * a screen has been shown.
 *
 * @param <T> the property value type
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Property<T> {
    private T value;
    private Observable<T> observable;

    /**
     * Creates a property with an initial literal value.
     *
     * @param value the initial value
     */
    public Property(T value) {
        this.value = value;
    }

    /**
    /**
     * Gets the stored value snapshot of this property.
     * <p>
     * When this property is bound to an observable, the returned value is the snapshot captured when the property
     * was last configured with {@link #set(Object)} or {@link #bind(Observable)}.
     *
     * @return the stored value snapshot
     */
    public T value() {
        return value;
    }

    /**
    /**
     * Gets the observable backing this property.
     *
     * @return the observable, or {@code null} if this property currently holds a literal value
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
    /**
     * Binds this property to an observable and snapshots its current value as the initial value.
     *
     * @param observable the observable binding
     */
    public void bind(Observable<T> observable) {
        this.value = observable.get();
        this.observable = observable;
    }
}
