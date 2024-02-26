package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Label extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "label";
    @Setter
    @Getter
    private String text;

    public Label(String text) {
        this.text = text;
    }
}
