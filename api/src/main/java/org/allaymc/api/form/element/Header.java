package org.allaymc.api.form.element;

import lombok.Getter;

/**
 * {@code Header} represents a section heading in a JSON form. Its serialized text is read-only
 * content, and custom-form default synchronization ignores the heading's response position because
 * the heading has no editable value to preserve.
 *
 * @author daoge_cmd
 */
public final class Header implements SimpleFormElement, CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "header";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;

    /**
     * Creates a form heading containing the supplied read-only display text.
     */
    public Header(String text) {
        this.text = text;
    }

    /**
     * Leaves this decorative entry unchanged because it has no editable default value.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
    }
}
