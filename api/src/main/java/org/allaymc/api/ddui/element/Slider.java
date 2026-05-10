package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * Integer slider element with configurable minimum and maximum bounds.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Slider extends ValueElementBase<Slider, Long> {
    private final Property<Long> min = new Property<>(0L);
    private final Property<Long> max = new Property<>(0L);

    /**
     * Creates a slider with the minimum value as its initial value.
     *
     * @param label the slider label
     * @param min   the minimum value
     * @param max   the maximum value
     */
    public Slider(String label, long min, long max) {
        this(label, min, max, min);
    }

    /**
     * Creates a slider with a literal initial value.
     *
     * @param label the slider label
     * @param min   the minimum value
     * @param max   the maximum value
     * @param value the initial value
     */
    public Slider(String label, long min, long max, long value) {
        super(value);
        label(label);
        min(min);
        max(max);
    }

    /**
     * Creates a slider whose value is bound to an observable.
     *
     * @param label the slider label
     * @param value the value observable
     * @param min   the minimum value
     * @param max   the maximum value
     */
    public Slider(String label, Observable<Long> value, long min, long max) {
        this(label, min, max, value.get());
        value(value);
    }

    /**
     * Creates a slider whose value and range are bound to observables.
     *
     * @param label the slider label
     * @param value the value observable
     * @param min   the minimum-value observable
     * @param max   the maximum-value observable
     */
    public Slider(String label, Observable<Long> value, Observable<Long> min, Observable<Long> max) {
        this(label, min.get(), max.get(), value.get());
        value(value);
        min(min);
        max(max);
    }

    /**
     * Gets the minimum-value property of this slider.
     *
     * @return the minimum-value property
     */
    public Property<Long> getMin() {
        return min;
    }

    /**
     * Sets the minimum slider value.
     *
     * @param min the minimum slider value
     * @return this element
     */
    public Slider min(long min) {
        this.min.set(min);
        return this;
    }

    /**
     * Binds the minimum slider value to an observable.
     *
     * @param min the minimum-value observable
     * @return this element
     */
    public Slider min(Observable<Long> min) {
        this.min.bind(min);
        return this;
    }

    /**
     * Gets the maximum-value property of this slider.
     *
     * @return the maximum-value property
     */
    public Property<Long> getMax() {
        return max;
    }

    /**
     * Sets the maximum slider value.
     *
     * @param max the maximum slider value
     * @return this element
     */
    public Slider max(long max) {
        this.max.set(max);
        return this;
    }

    /**
     * Binds the maximum slider value to an observable.
     *
     * @param max the maximum-value observable
     * @return this element
     */
    public Slider max(Observable<Long> max) {
        this.max.bind(max);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Slider self() {
        return this;
    }
}
