package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * {@code Slider} represents an integer-valued custom-form slider with configurable lower and upper
 * bounds. Its value and bounds can use observable sources; template construction and setters store
 * supplied values without checking range ordering or clamping the selection.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Slider extends ValueElementBase<Slider, Long> {
    /**
     * Slider lower-bound template property.
     */
    private final Property<Long> min = new Property<>(0L);
    /**
     * Slider upper-bound template property.
     */
    private final Property<Long> max = new Property<>(0L);

    /**
     * Creates a slider template with the supplied label and integer range. The initial selection
     * uses {@code min}; construction does not validate bound ordering or clamp the selected value.
     */
    public Slider(String label, long min, long max) {
        this(label, min, max, min);
    }

    /**
     * Creates a slider template with the supplied label and integer range. The initial selection
     * uses {@code value}; construction does not validate bound ordering or clamp the selected
     * value.
     */
    public Slider(String label, long min, long max, long value) {
        super(value);
        label(label);
        min(min);
        max(max);
    }

    /**
     * Creates a slider template using the supplied observable configuration. Construction captures
     * current source values; runtime sessions subscribe when compiled, and later literal template
     * changes do not replace those existing subscriptions.
     */
    public Slider(String label, Observable<Long> value, long min, long max) {
        this(label, min, max, value.get());
        value(value);
    }

    /**
     * Creates a slider template using the supplied observable configuration. Construction captures
     * current source values; runtime sessions subscribe when compiled, and later literal template
     * changes do not replace those existing subscriptions.
     */
    public Slider(String label, Observable<Long> value, Observable<Long> min, Observable<Long> max) {
        this(label, min.get(), max.get(), value.get());
        value(value);
        min(min);
        max(max);
    }

    /**
     * Returns this element's mutable {@code min} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<Long> getMin() {
        return min;
    }

    /**
     * Stores the supplied min template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public Slider min(long min) {
        this.min.set(min);
        return this;
    }

    /**
     * Binds the min template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public Slider min(Observable<Long> min) {
        this.min.bind(min);
        return this;
    }

    /**
     * Returns this element's mutable {@code max} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<Long> getMax() {
        return max;
    }

    /**
     * Stores the supplied max template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public Slider max(long max) {
        this.max.set(max);
        return this;
    }

    /**
     * Binds the max template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public Slider max(Observable<Long> max) {
        this.max.bind(max);
        return this;
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Slider self() {
        return this;
    }
}
