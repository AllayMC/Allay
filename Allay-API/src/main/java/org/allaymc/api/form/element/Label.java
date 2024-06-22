package org.allaymc.api.form.element;

import lombok.Getter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Label extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "label";

    @Getter
    private final String text;

    public Label(String text) {
        this.text = text;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        // Do nothing
    }
}
