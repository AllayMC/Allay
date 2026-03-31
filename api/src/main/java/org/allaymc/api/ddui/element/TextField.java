package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI text-field element.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class TextField extends ValueElementBase<TextField, String> {
    /**
     * Creates a text field with an empty initial value.
     *
     * @param label the field label
     */
    public TextField(String label) {
        super("");
        label(label);
    }

    /**
     * Creates a text field bound to an observable.
     *
     * @param label the field label
     * @param text  the text observable
     */
    public TextField(String label, Observable<String> text) {
        this(label);
        value(text);
    }

    /**
     * Sets the current text value.
     *
     * @param text the text value
     * @return this element
     */
    public TextField text(String text) {
        return value(text);
    }

    /**
     * Binds the text value to an observable.
     *
     * @param text the text observable
     * @return this element
     */
    public TextField text(Observable<String> text) {
        return value(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TextField self() {
        return this;
    }
}
