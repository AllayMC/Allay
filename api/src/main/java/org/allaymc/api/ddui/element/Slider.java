package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI slider element.
 *
 * @author OpenAI
 */
public final class Slider extends ValueElementBase<Slider, Long> {
    private long min;
    private Observable<Long> minObservable;
    private long max;
    private Observable<Long> maxObservable;

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
     * Gets the minimum slider value.
     *
     * @return the minimum slider value
     */
    public long getMin() {
        return min;
    }

    /**
     * Gets the observable bound to the minimum slider value.
     *
     * @return the minimum-value observable, or {@code null} if the minimum is not observable-backed
     */
    public Observable<Long> getMinObservable() {
        return minObservable;
    }

    /**
     * Sets the minimum slider value.
     *
     * @param min the minimum slider value
     * @return this element
     */
    public Slider min(long min) {
        this.min = min;
        this.minObservable = null;
        return this;
    }

    /**
     * Binds the minimum slider value to an observable.
     *
     * @param min the minimum-value observable
     * @return this element
     */
    public Slider min(Observable<Long> min) {
        this.min = min.get();
        this.minObservable = min;
        return this;
    }

    /**
     * Gets the maximum slider value.
     *
     * @return the maximum slider value
     */
    public long getMax() {
        return max;
    }

    /**
     * Gets the observable bound to the maximum slider value.
     *
     * @return the maximum-value observable, or {@code null} if the maximum is not observable-backed
     */
    public Observable<Long> getMaxObservable() {
        return maxObservable;
    }

    /**
     * Sets the maximum slider value.
     *
     * @param max the maximum slider value
     * @return this element
     */
    public Slider max(long max) {
        this.max = max;
        this.maxObservable = null;
        return this;
    }

    /**
     * Binds the maximum slider value to an observable.
     *
     * @param max the maximum-value observable
     * @return this element
     */
    public Slider max(Observable<Long> max) {
        this.max = max.get();
        this.maxObservable = max;
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
