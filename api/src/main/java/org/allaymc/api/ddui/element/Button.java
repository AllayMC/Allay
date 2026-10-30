package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * {@code Button} represents a clickable control in a custom-form layout. Its label, tooltip,
 * visibility, and disabled state are template properties; observable bindings can update opened
 * sessions, while click callbacks receive the session in which the action occurred.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Button extends ButtonElementBase<Button> {
    /**
     * Button hover text property.
     */
    private final Property<String> tooltip = new Property<>("");
    /**
     * Control disabled-state template property.
     */
    private final Property<Boolean> disabled = new Property<>(false);

    /**
     * Creates a button template with the supplied literal label value.
     */
    public Button(String label) {
        label(label);
    }

    /**
     * Creates a button template using the supplied observable configuration. Construction captures
     * current source values; runtime sessions subscribe when compiled, and later literal template
     * changes do not replace those existing subscriptions.
     */
    public Button(Observable<String> label) {
        label(label);
    }

    /**
     * Returns this element's mutable {@code tooltip} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<String> getTooltip() {
        return tooltip;
    }

    /**
     * Stores the supplied tooltip template value and returns this element for further
     * configuration. This operation clears that property's observable binding and does not send
     * updates to existing sessions.
     */
    public Button tooltip(String tooltip) {
        this.tooltip.set(tooltip);
        return this;
    }

    /**
     * Binds the tooltip template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public Button tooltip(Observable<String> tooltip) {
        this.tooltip.bind(tooltip);
        return this;
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
    public Button disabled(boolean disabled) {
        this.disabled.set(disabled);
        return this;
    }

    /**
     * Binds the disabled template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public Button disabled(Observable<Boolean> disabled) {
        this.disabled.bind(disabled);
        return this;
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Button self() {
        return this;
    }
}
