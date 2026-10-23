package org.allaymc.api.form.element;

import lombok.Getter;

/**
 * {@code Label} represents read-only text in a JSON form layout. Its serialized text supplies
 * content without an editable default, so custom-form default synchronization ignores the label's
 * response position and leaves the text unchanged.
 *
 * @author daoge_cmd
 */
public final class Label implements SimpleFormElement, CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "label";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;

    /**
     * Creates a form label containing the supplied read-only display text.
     */
    public Label(String text) {
        this.text = text;
    }

    /**
     * Leaves this decorative entry unchanged because it has no editable default value.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
    }
}
