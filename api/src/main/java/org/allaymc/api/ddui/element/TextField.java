package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * {@code TextField} represents an editable text control in a custom-form layout. Its inherited
 * value property holds the initial text or observable binding, and {@code text} aliases the value
 * configuration while change callbacks receive client-originated session values.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class TextField extends ValueElementBase<TextField, String> {
    /**
     * Creates a text-field template with the supplied label and empty text.
     */
    public TextField(String label) {
        super("");
        label(label);
    }

    /**
     * Creates a textfield template using the supplied observable configuration. Construction
     * captures current source values; runtime sessions subscribe when compiled, and later literal
     * template changes do not replace those existing subscriptions.
     */
    public TextField(String label, Observable<String> text) {
        this(label);
        value(text);
    }

    /**
     * Stores the supplied literal in the inherited value property and returns this element. This
     * alias clears that property's observable binding, while existing sessions keep their current
     * compiled values.
     */
    public TextField text(String text) {
        return value(text);
    }

    /**
     * Binds the inherited value property to the supplied observable and returns this element. This
     * alias uses the same configuration as {@code value}; existing sessions keep the binding
     * compiled when they opened.
     */
    public TextField text(Observable<String> text) {
        return value(text);
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected TextField self() {
        return this;
    }
}
