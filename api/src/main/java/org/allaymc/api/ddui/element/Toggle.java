package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI toggle element.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Toggle extends ValueElementBase<Toggle, Boolean> {
    /**
     * Creates a toggle with a {@code false} initial value.
     *
     * @param label the toggle label
     */
    public Toggle(String label) {
        super(false);
        label(label);
    }

    /**
     * Creates a toggle bound to an observable.
     *
     * @param label   the toggle label
     * @param toggled the value observable
     */
    public Toggle(String label, Observable<Boolean> toggled) {
        this(label);
        value(toggled);
    }

    /**
     * Checks whether the toggle is currently enabled.
     *
     * @return {@code true} if the toggle is enabled
     */
    public boolean isToggled() {
        return Boolean.TRUE.equals(getValue());
    }

    /**
     * Gets the observable bound to the toggle value.
     *
     * @return the toggle observable, or {@code null} if the value is not observable-backed
     */
    public Observable<Boolean> getToggledObservable() {
        return getValueObservable();
    }

    /**
     * Sets the toggle value.
     *
     * @param toggled the toggle value
     * @return this element
     */
    public Toggle toggled(boolean toggled) {
        return value(toggled);
    }

    /**
     * Binds the toggle value to an observable.
     *
     * @param toggled the toggle observable
     * @return this element
     */
    public Toggle toggled(Observable<Boolean> toggled) {
        return value(toggled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Toggle self() {
        return this;
    }
}
