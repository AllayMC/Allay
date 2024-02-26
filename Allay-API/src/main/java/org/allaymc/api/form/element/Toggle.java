package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Toggle extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "toggle";
    @Getter
    private final String text;
    @Getter
    @SerializedName("default")
    private final boolean defaultValue;

    public Toggle(String text, boolean defaultValue) {
        this.text = text;
        this.defaultValue = defaultValue;
    }
}
