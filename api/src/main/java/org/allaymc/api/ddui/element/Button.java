package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BindableValue;

/**
 * A DDUI button element.
 *
 * @author OpenAI
 */
public final class Button extends ButtonElementBase<Button> {
    private final BindableValue<String> tooltip = new BindableValue<>("");
    private final BindableValue<Boolean> disabled = new BindableValue<>(false);

    /**
     * Creates a button with a literal label.
     *
     * @param label the button label
     */
    public Button(String label) {
        label(label);
    }

    /**
     * Creates a button bound to an observable label.
     *
     * @param label the label observable
     */
    public Button(Observable<String> label) {
        label(label);
    }

    /**
     * Gets the button tooltip.
     *
     * @return the button tooltip
     */
    public String getTooltip() {
        return tooltip.value();
    }

    /**
     * Gets the observable bound to the button tooltip.
     *
     * @return the tooltip observable, or {@code null} if the tooltip is not observable-backed
     */
    public Observable<String> getTooltipObservable() {
        return tooltip.observable();
    }

    /**
     * Sets the button tooltip.
     *
     * @param tooltip the button tooltip
     * @return this element
     */
    public Button tooltip(String tooltip) {
        this.tooltip.set(tooltip);
        return this;
    }

    /**
     * Binds the button tooltip to an observable.
     *
     * @param tooltip the tooltip observable
     * @return this element
     */
    public Button tooltip(Observable<String> tooltip) {
        this.tooltip.bind(tooltip);
        return this;
    }

    /**
     * Checks whether this button is disabled by default.
     *
     * @return {@code true} if this button is disabled
     */
    public boolean isDisabled() {
        return disabled.value();
    }

    /**
     * Gets the observable bound to the button's disabled state.
     *
     * @return the disabled observable, or {@code null} if the disabled state is not observable-backed
     */
    public Observable<Boolean> getDisabledObservable() {
        return disabled.observable();
    }

    /**
     * Sets whether this button is disabled.
     *
     * @param disabled the disabled state
     * @return this element
     */
    public Button disabled(boolean disabled) {
        this.disabled.set(disabled);
        return this;
    }

    /**
     * Binds the button's disabled state to an observable.
     *
     * @param disabled the disabled observable
     * @return this element
     */
    public Button disabled(Observable<Boolean> disabled) {
        this.disabled.bind(disabled);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Button self() {
        return this;
    }
}
