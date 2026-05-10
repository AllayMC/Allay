package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * Editable text-field element.
 * <p>
 * The entered text is exposed through the inherited {@link #getValue()} property.
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
     * Convenience alias of {@code value(text)}.
     *
     * @param text the text value
     * @return this element
     */
    public TextField text(String text) {
        return value(text);
    }

    /**
     * Convenience alias of {@code value(text)} for observable bindings.
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
