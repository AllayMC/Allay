package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * {@code Toggle} represents a boolean control in a custom-form layout. Its inherited value property
 * holds the initial checked state or observable binding, and {@code toggled} aliases value
 * configuration while change callbacks receive client-originated session values.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Toggle extends ValueElementBase<Toggle, Boolean> {
    /**
     * Creates a toggle template with the supplied label and an unchecked value.
     */
    public Toggle(String label) {
        super(false);
        label(label);
    }

    /**
     * Creates a toggle template using the supplied observable configuration. Construction captures
     * current source values; runtime sessions subscribe when compiled, and later literal template
     * changes do not replace those existing subscriptions.
     */
    public Toggle(String label, Observable<Boolean> toggled) {
        this(label);
        value(toggled);
    }

    /**
     * Stores the supplied literal in the inherited value property and returns this element. This
     * alias clears that property's observable binding, while existing sessions keep their current
     * compiled values.
     */
    public Toggle toggled(boolean toggled) {
        return value(toggled);
    }

    /**
     * Binds the inherited value property to the supplied observable and returns this element. This
     * alias uses the same configuration as {@code value}; existing sessions keep the binding
     * compiled when they opened.
     */
    public Toggle toggled(Observable<Boolean> toggled) {
        return value(toggled);
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Toggle self() {
        return this;
    }
}
