package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * {@code Toggle} represents a custom-form boolean input with label text and an optional tooltip.
 * Its stored default is serialized when the form is displayed, and server-settings synchronization
 * updates that default using the response token's boolean interpretation.
 *
 * @author daoge_cmd
 */
public final class Toggle implements CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "toggle";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;
    /**
     * Value used for the next display.
     */
    @Getter
    @SerializedName("default")
    private boolean defaultValue;
    /**
     * Optional control hover text.
     */
    @Getter
    private final String tooltip;

    /**
     * Creates a toggle with the supplied label, default state, and tooltip. A {@code null} tooltip
     * is allowed, and later server-settings synchronization can replace the stored boolean default.
     */
    public Toggle(String text, boolean defaultValue, String tooltip) {
        this.text = text;
        this.defaultValue = defaultValue;
        this.tooltip = tooltip;
    }

    /**
     * Stores the response's boolean interpretation as the next displayed default. Only a
     * case-insensitive {@code "true"} becomes {@code true}; other strings, including {@code null}, become {@code false} without a parsing exception.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultValue = Boolean.parseBoolean(response);
    }
}
