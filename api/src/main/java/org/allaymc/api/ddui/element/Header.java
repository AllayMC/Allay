package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * {@code Header} represents a section heading in a custom-form layout. Its text and inherited
 * visibility are template properties, allowing callers to configure literal content before opening
 * a screen or bind observable values for later runtime updates.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Header extends TextElementBase<Header> {
    /**
     * Creates a header template with the supplied literal text value.
     */
    public Header(String text) {
        text(text);
    }

    /**
     * Creates a header template using the supplied observable configuration. Construction captures
     * current source values; runtime sessions subscribe when compiled, and later literal template
     * changes do not replace those existing subscriptions.
     */
    public Header(Observable<String> text) {
        text(text);
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Header self() {
        return this;
    }
}
