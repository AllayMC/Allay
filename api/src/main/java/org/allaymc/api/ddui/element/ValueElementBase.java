package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BindableValue;
import org.allaymc.api.ddui.session.DDUIScreenSession;

import java.util.function.BiConsumer;

/**
 * Shared base implementation for DDUI value elements.
 *
 * @param <E> the concrete element type
 * @param <T> the value type
 *
 * @author daoge_cmd | SerenityJS
 */
non-sealed abstract class ValueElementBase<E extends ValueElementBase<E, T>, T> extends ElementBase<E> implements ValueElement<T> {
    private final BindableValue<String> label = new BindableValue<>("");
    private final BindableValue<String> description = new BindableValue<>("");
    private final BindableValue<Boolean> disabled = new BindableValue<>(false);
    private final BindableValue<T> value;
    private BiConsumer<DDUIScreenSession, T> onChange = (session, value) -> {
    };

    /**
     * Creates a value element with the provided initial value.
     *
     * @param value the initial value
     */
    protected ValueElementBase(T value) {
        this.value = new BindableValue<>(value);
    }

    /**
     * Gets the label shown by this element.
     *
     * @return the element label
     */
    public String getLabel() {
        return label.value();
    }

    /**
     * Gets the observable bound to this element's label.
     *
     * @return the label observable, or {@code null} if the label is not observable-backed
     */
    public Observable<String> getLabelObservable() {
        return label.observable();
    }

    /**
     * Sets the label shown by this element.
     *
     * @param label the label text
     * @return this element
     */
    public E label(String label) {
        this.label.set(label);
        return self();
    }

    /**
     * Binds this element's label to an observable.
     *
     * @param label the label observable
     * @return this element
     */
    public E label(Observable<String> label) {
        this.label.bind(label);
        return self();
    }

    /**
     * Gets the description shown by this element.
     *
     * @return the element description
     */
    public String getDescription() {
        return description.value();
    }

    /**
     * Gets the observable bound to this element's description.
     *
     * @return the description observable, or {@code null} if the description is not observable-backed
     */
    public Observable<String> getDescriptionObservable() {
        return description.observable();
    }

    /**
     * Sets the description shown by this element.
     *
     * @param description the description text
     * @return this element
     */
    public E description(String description) {
        this.description.set(description);
        return self();
    }

    /**
     * Binds this element's description to an observable.
     *
     * @param description the description observable
     * @return this element
     */
    public E description(Observable<String> description) {
        this.description.bind(description);
        return self();
    }

    /**
     * Checks whether this element is disabled by default.
     *
     * @return {@code true} if this element is disabled
     */
    public boolean isDisabled() {
        return disabled.value();
    }

    /**
     * Gets the observable bound to this element's disabled state.
     *
     * @return the disabled observable, or {@code null} if the disabled state is not observable-backed
     */
    public Observable<Boolean> getDisabledObservable() {
        return disabled.observable();
    }

    /**
     * Sets whether this element is disabled.
     *
     * @param disabled the disabled state
     * @return this element
     */
    public E disabled(boolean disabled) {
        this.disabled.set(disabled);
        return self();
    }

    /**
     * Binds this element's disabled state to an observable.
     *
     * @param disabled the disabled observable
     * @return this element
     */
    public E disabled(Observable<Boolean> disabled) {
        this.disabled.bind(disabled);
        return self();
    }

    /**
     * Gets the current value of this element.
     *
     * @return the element value
     */
    public T getValue() {
        return value.value();
    }

    /**
     * Gets the observable bound to this element's value.
     *
     * @return the value observable, or {@code null} if the value is not observable-backed
     */
    public Observable<T> getValueObservable() {
        return value.observable();
    }

    /**
     * Sets the current value of this element.
     *
     * @param value the element value
     * @return this element
     */
    public E value(T value) {
        this.value.set(value);
        return self();
    }

    /**
     * Binds this element's value to an observable.
     *
     * @param value the value observable
     * @return this element
     */
    public E value(Observable<T> value) {
        this.value.bind(value);
        return self();
    }

    /**
     * Gets the callback that runs when the client changes this element.
     *
     * @return the change callback
     */
    public BiConsumer<DDUIScreenSession, T> getOnChange() {
        return onChange;
    }

    /**
     * Sets the callback that runs when the client changes this element.
     *
     * @param onChange the change callback
     * @return this element
     */
    public E onChange(BiConsumer<DDUIScreenSession, T> onChange) {
        this.onChange = onChange;
        return self();
    }
}
