package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

import java.util.function.BiConsumer;

/**
 * Shared base implementation for editable value elements.
 *
 * @param <E> the concrete element type
 * @param <T> the value type
 *
 * @author daoge_cmd | SerenityJS
 */
non-sealed abstract class ValueElementBase<E extends ValueElementBase<E, T>, T> extends ElementBase<E> implements ValueElement<T> {
    private final Property<String> label = new Property<>("");
    private final Property<String> description = new Property<>("");
    private final Property<Boolean> disabled = new Property<>(false);
    private final Property<T> value;
    private BiConsumer<DDUIScreenSession, T> onChange = (session, value) -> {
    };

    /**
     * Creates a value element with the provided initial value.
     *
     * @param value the initial value
     */
    protected ValueElementBase(T value) {
        this.value = new Property<>(value);
    }

    /**
     * Gets the label property of this element.
     *
     * @return the label property
     */
    public Property<String> getLabel() {
        return label;
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
     * Gets the description property of this element.
     *
     * @return the description property
     */
    public Property<String> getDescription() {
        return description;
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
     * Gets the disabled-state property of this element.
     *
     * @return the disabled-state property
     */
    public Property<Boolean> getDisabled() {
        return disabled;
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
     * Gets the value property of this element.
     *
     * @return the value property
     */
    public Property<T> getValue() {
        return value;
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
     * Gets the callback that runs when the viewer changes this element in an opened session.
     *
     * @return the change callback
     */
    public BiConsumer<DDUIScreenSession, T> getOnChange() {
        return onChange;
    }

    /**
     * Sets the callback that runs when the viewer changes this element in an opened session.
     * <p>
     * Server-side updates performed through {@link org.allaymc.api.ddui.DDUIScreenSession#set(ValueElement, Object)}
     * do not trigger this callback.
     *
     * @param onChange the change callback
     * @return this element
     */
    public E onChange(BiConsumer<DDUIScreenSession, T> onChange) {
        this.onChange = onChange;
        return self();
    }
}
