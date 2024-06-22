package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Input extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "input";

    @Getter
    private final String text;
    @Getter
    private final String placeholder;
    @Getter
    @SerializedName("default")
    private String defaultText;

    public Input(String text, String placeholder, String defaultText) {
        this.text = text;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultText = response;
    }
}
