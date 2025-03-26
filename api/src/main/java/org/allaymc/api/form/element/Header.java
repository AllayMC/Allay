package org.allaymc.api.form.element;

import lombok.Getter;

/**
 * @author daoge_cmd
 */
public final class Header implements SimpleFormElement, CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "header";

    @Getter
    private final String text;

    /**
     * Create a new divider.
     *
     * @param text the text of the divider.
     */
    public Header(String text) {
        this.text = text;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        // Do nothing
    }
}
