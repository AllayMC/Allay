package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * Shared base implementation for non-interactive text elements.
 *
 * @param <E> the concrete element type
 *
 * @author daoge_cmd | SerenityJS
 */
abstract class TextElementBase<E extends TextElementBase<E>> extends ElementBase<E> {
    private final Property<String> text = new Property<>("");

    /**
     * Gets the text property shown by this element.
     *
     * @return the text property
     */
    public Property<String> getText() {
        return text;
    }

    /**
     * Sets the text shown by this element.
     *
     * @param text the element text
     * @return this element
     */
    public E text(String text) {
        this.text.set(text);
        return self();
    }

    /**
     * Binds this element's text to an observable.
     *
     * @param text the text observable
     * @return this element
     */
    public E text(Observable<String> text) {
        this.text.bind(text);
        return self();
    }
}
