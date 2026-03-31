package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * A DDUI header element.
 *
 * @author OpenAI
 */
public final class Header extends TextElementBase<Header> {
    /**
     * Creates a header with literal text.
     *
     * @param text the header text
     */
    public Header(String text) {
        text(text);
    }

    /**
     * Creates a header bound to an observable text.
     *
     * @param text the header observable
     */
    public Header(Observable<String> text) {
        text(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Header self() {
        return this;
    }
}
