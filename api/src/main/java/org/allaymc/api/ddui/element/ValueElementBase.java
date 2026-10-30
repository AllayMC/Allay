package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

import java.util.function.BiConsumer;

/**
 * {@code ValueElementBase} supplies common label, description, disabled-state, and value properties
 * for editable DDUI controls. Templates hold initial configuration, while opened sessions retain
 * their own current values. Client-change callbacks receive the session and normalized value;
 * server session updates do not invoke them.
 *
 * @author daoge_cmd | SerenityJS
 */
non-sealed abstract class ValueElementBase<E extends ValueElementBase<E, T>, T> extends ElementBase<E> implements ValueElement<T> {
    /**
     * Control label template property.
     */
    private final Property<String> label = new Property<>("");
    /**
     * Secondary control description property.
     */
    private final Property<String> description = new Property<>("");
    /**
     * Control disabled-state template property.
     */
    private final Property<Boolean> disabled = new Property<>(false);
    /**
     * Initial control value property.
     */
    private final Property<T> value;
    /**
     * {@code onChange} receives client-originated session values. Server session updates bypass it,
     * and callback failures propagate after the session value changes.
     */
    private BiConsumer<DDUIScreenSession, T> onChange = (session, value) -> {
    };

    /**
     * Creates an editable template element holding the supplied initial value.
     */
    protected ValueElementBase(T value) {
        this.value = new Property<>(value);
    }

    /**
     * Returns this element's mutable {@code label} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<String> getLabel() {
        return label;
    }

    /**
     * Stores the supplied label template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public E label(String label) {
        this.label.set(label);
        return self();
    }

    /**
     * Binds the label template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E label(Observable<String> label) {
        this.label.bind(label);
        return self();
    }

    /**
     * Returns this element's mutable {@code description} template property. Reconfiguring that
     * property affects subsequent session compilation; existing sessions retain their current
     * compiled binding and do not receive literal template changes.
     */
    public Property<String> getDescription() {
        return description;
    }

    /**
     * Stores the supplied description template value and returns this element for further
     * configuration. This operation clears that property's observable binding and does not send
     * updates to existing sessions.
     */
    public E description(String description) {
        this.description.set(description);
        return self();
    }

    /**
     * Binds the description template property to the supplied observable and returns this element.
     * New sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E description(Observable<String> description) {
        this.description.bind(description);
        return self();
    }

    /**
     * Returns this element's mutable {@code disabled} template property. Reconfiguring that
     * property affects subsequent session compilation; existing sessions retain their current
     * compiled binding and do not receive literal template changes.
     */
    public Property<Boolean> getDisabled() {
        return disabled;
    }

    /**
     * Stores the supplied disabled template value and returns this element for further
     * configuration. This operation clears that property's observable binding and does not send
     * updates to existing sessions.
     */
    public E disabled(boolean disabled) {
        this.disabled.set(disabled);
        return self();
    }

    /**
     * Binds the disabled template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E disabled(Observable<Boolean> disabled) {
        this.disabled.bind(disabled);
        return self();
    }

    /**
     * Returns this element's mutable {@code value} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<T> getValue() {
        return value;
    }

    /**
     * Stores the supplied value template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public E value(T value) {
        this.value.set(value);
        return self();
    }

    /**
     * Binds the value template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E value(Observable<T> value) {
        this.value.bind(value);
        return self();
    }

    /**
     * Returns the callback configured for client changes to this element.
     */
    public BiConsumer<DDUIScreenSession, T> getOnChange() {
        return onChange;
    }

    /**
     * Stores the callback for client-originated value changes and returns this element. Runtime
     * session writes do not invoke it; a client callback failure propagates after the current
     * runtime value has changed.
     */
    public E onChange(BiConsumer<DDUIScreenSession, T> onChange) {
        this.onChange = onChange;
        return self();
    }
}
