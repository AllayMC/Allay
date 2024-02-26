package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Toggle extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "toggle";
    @Setter
    @Getter
    private String text;
    @Setter
    @Getter
    @SerializedName("default")
    private boolean defaultValue;

    public Toggle(String text) {
        this(text, false);
    }

    public Toggle(String text, boolean defaultValue) {
        this.text = text;
        this.defaultValue = defaultValue;
    }
}
