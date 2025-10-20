package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Represents an input element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Input implements CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "input";

    @Getter
    private final String text;
    @Getter
    private final String placeholder;
    @Getter
    @SerializedName("default")
    private String defaultText;
    @Getter
    private final String tooltip;

    /**
     * Create a new input.
     *
     * @param text        the text of the input
     * @param placeholder the placeholder of the input
     * @param defaultText the default text of the input
     * @param tooltip     the tooltip of the input. Can be {@code null}
     */
    public Input(String text, String placeholder, String defaultText, String tooltip) {
        this.text = text;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
        this.tooltip = tooltip;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultText = response;
    }
}
