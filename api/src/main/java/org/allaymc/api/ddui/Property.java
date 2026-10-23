package org.allaymc.api.ddui;

/**
 * {@code Property} stores a DDUI template value and an optional observable binding. {@code bind}
 * captures the observable's current value, while runtime session compilation reads its latest
 * value; {@code value()} continues to expose the stored template snapshot until reconfiguration.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Property<T> {
    /**
     * Stored template value snapshot.
     */
    private T value;
    /**
     * {@code observable} supplies runtime updates when bound. {@code set} clears this reference
     * while replacing the stored literal snapshot.
     */
    private Observable<T> observable;

    /**
     * Creates an unbound template property holding the supplied value reference.
     */
    public Property(T value) {
        this.value = value;
    }

    /**
     * Returns the stored template snapshot without consulting a bound observable. Observable
     * updates do not refresh this snapshot; {@code set} and {@code bind} replace it when the
     * template is reconfigured.
     */
    public T value() {
        return value;
    }

    /**
     * Returns the observable currently bound to this template property. The result is {@code null}
     * for literal properties, and callers receive the same source reference supplied to
     * {@code bind}.
     */
    public Observable<T> observable() {
        return observable;
    }

    /**
     * Stores the supplied literal reference and clears the observable binding. Existing runtime
     * sessions keep their compiled bindings, so this template change affects subsequent compilation
     * rather than sending a live update.
     */
    public void set(T value) {
        this.value = value;
        this.observable = null;
    }

    /**
     * Binds the supplied observable and captures its current value as the template snapshot.
     * Existing sessions retain their compiled bindings; a {@code null} observable fails before
     * either stored property reference changes.
     */
    public void bind(Observable<T> observable) {
        this.value = observable.get();
        this.observable = observable;
    }
}
