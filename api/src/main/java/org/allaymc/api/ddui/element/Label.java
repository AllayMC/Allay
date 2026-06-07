package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * Static label text element used to show read-only content inside a custom form.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Label extends TextElementBase<Label> {
    /**
     * Creates a label with literal text.
     *
     * @param text the label text
     */
    public Label(String text) {
        text(text);
    }

    /**
     * Creates a label bound to an observable text.
     *
     * @param text the label observable
     */
    public Label(Observable<String> text) {
        text(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Label self() {
        return this;
    }
}
