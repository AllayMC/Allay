package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * A DDUI button element.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Button extends ButtonElementBase<Button> {
    private final Property<String> tooltip = new Property<>("");
    private final Property<Boolean> disabled = new Property<>(false);

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
     * Gets the bindable button tooltip property.
     *
     * @return the tooltip property
     */
    public Property<String> getTooltip() {
        return tooltip;
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
     * Gets the bindable disabled-state property of this button.
     *
     * @return the disabled-state property
     */
    public Property<Boolean> getDisabled() {
        return disabled;
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
