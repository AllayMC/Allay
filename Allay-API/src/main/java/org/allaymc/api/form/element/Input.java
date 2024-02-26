package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Input extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "input";
    @Setter
    @Getter
    private String text;
    @Getter
    @Setter
    private String placeholder;
    @Setter
    @Getter
    @SerializedName("default")
    private String defaultText;

    public Input(String text) {
        this(text, "");
    }

    public Input(String text, String placeholder) {
        this(text, placeholder, "");
    }

    public Input(String text, String placeholder, String defaultText) {
        this.text = text;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
    }
}
