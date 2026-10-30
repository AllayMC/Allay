package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;

/**
 * {@code TextElementBase} supplies text configuration for noninteractive DDUI elements. Concrete
 * labels and headers share a mutable template property; a literal sets initial content, while an
 * observable binding supplies values when runtime sessions compile and later receive updates.
 *
 * @author daoge_cmd | SerenityJS
 */
abstract class TextElementBase<E extends TextElementBase<E>> extends ElementBase<E> {
    /**
     * Displayed text template property.
     */
    private final Property<String> text = new Property<>("");

    /**
     * Returns this element's mutable {@code text} template property. Reconfiguring that property
     * affects subsequent session compilation; existing sessions retain their current compiled
     * binding and do not receive literal template changes.
     */
    public Property<String> getText() {
        return text;
    }

    /**
     * Stores the supplied text template value and returns this element for further configuration.
     * This operation clears that property's observable binding and does not send updates to
     * existing sessions.
     */
    public E text(String text) {
        this.text.set(text);
        return self();
    }

    /**
     * Binds the text template property to the supplied observable and returns this element. New
     * sessions subscribe to that source; this reconfiguration does not replace bindings already
     * compiled by existing sessions.
     */
    public E text(Observable<String> text) {
        this.text.bind(text);
        return self();
    }
}
