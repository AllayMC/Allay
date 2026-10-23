package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * {@code Input} represents a custom-form text input with a label, placeholder, initial text, and
 * optional tooltip. Server-settings synchronization stores the corresponding response string as the
 * next default text, preserving the value already decoded by custom-form response parsing.
 *
 * @author daoge_cmd
 */
public final class Input implements CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "input";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;
    /**
     * Hint displayed for empty input.
     */
    @Getter
    private final String placeholder;
    /**
     * Text used for the next display.
     */
    @Getter
    @SerializedName("default")
    private String defaultText;
    /**
     * Optional control hover text.
     */
    @Getter
    private final String tooltip;

    /**
     * Creates a text input with the supplied label, placeholder, default text, and tooltip. A
     * {@code null} tooltip is allowed; supplied text values are retained without further validation
     * or conversion.
     */
    public Input(String text, String placeholder, String defaultText, String tooltip) {
        this.text = text;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
        this.tooltip = tooltip;
    }

    /**
     * Stores the supplied response string unchanged as the next displayed default text. Custom-form
     * response parsing decodes JSON string values before this call, and this method performs no
     * additional unescaping or validation.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultText = response;
    }
}
