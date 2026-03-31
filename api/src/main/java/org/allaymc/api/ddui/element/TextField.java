package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI text-field element.
 *
 * @author OpenAI
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
     * Gets the current text value.
     *
     * @return the current text value
     */
    public String getText() {
        return getValue();
    }

    /**
     * Gets the observable bound to the text value.
     *
     * @return the text observable, or {@code null} if the text is not observable-backed
     */
    public Observable<String> getTextObservable() {
        return getValueObservable();
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
