package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI button element.
 *
 * @author OpenAI
 */
public final class Button extends ButtonElementBase<Button> {
    private String tooltip = "";
    private Observable<String> tooltipObservable;
    private boolean disabled;
    private Observable<Boolean> disabledObservable;

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
        return tooltip;
    }

    /**
     * Gets the observable bound to the button tooltip.
     *
     * @return the tooltip observable, or {@code null} if the tooltip is not observable-backed
     */
    public Observable<String> getTooltipObservable() {
        return tooltipObservable;
    }

    /**
     * Sets the button tooltip.
     *
     * @param tooltip the button tooltip
     * @return this element
     */
    public Button tooltip(String tooltip) {
        this.tooltip = tooltip;
        this.tooltipObservable = null;
        return this;
    }

    /**
     * Binds the button tooltip to an observable.
     *
     * @param tooltip the tooltip observable
     * @return this element
     */
    public Button tooltip(Observable<String> tooltip) {
        this.tooltip = tooltip.get();
        this.tooltipObservable = tooltip;
        return this;
    }

    /**
     * Checks whether this button is disabled by default.
     *
     * @return {@code true} if this button is disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Gets the observable bound to the button's disabled state.
     *
     * @return the disabled observable, or {@code null} if the disabled state is not observable-backed
     */
    public Observable<Boolean> getDisabledObservable() {
        return disabledObservable;
    }

    /**
     * Sets whether this button is disabled.
     *
     * @param disabled the disabled state
     * @return this element
     */
    public Button disabled(boolean disabled) {
        this.disabled = disabled;
        this.disabledObservable = null;
        return this;
    }

    /**
     * Binds the button's disabled state to an observable.
     *
     * @param disabled the disabled observable
     * @return this element
     */
    public Button disabled(Observable<Boolean> disabled) {
        this.disabled = disabled.get();
        this.disabledObservable = disabled;
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
