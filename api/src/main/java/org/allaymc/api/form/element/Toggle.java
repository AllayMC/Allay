package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Represents a toggle element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Toggle implements CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "toggle";

    @Getter
    private final String text;
    @Getter
    @SerializedName("default")
    private boolean defaultValue;
    @Getter
    private final String tooltip;

    /**
     * Create a new toggle.
     *
     * @param text         the text of the toggle
     * @param defaultValue the default value of the toggle
     */
    public Toggle(String text, boolean defaultValue, String tooltip) {
        this.text = text;
        this.defaultValue = defaultValue;
        this.tooltip = tooltip;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultValue = Boolean.parseBoolean(response);
    }
}
