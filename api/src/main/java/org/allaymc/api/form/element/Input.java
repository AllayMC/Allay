package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Represents an input element in a {@link org.allaymc.api.form.type.CustomForm}.
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

    /**
     * Create a new input.
     *
     * @param text        the text of the input.
     * @param placeholder the placeholder of the input.
     * @param defaultText the default text of the input.
     */
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
