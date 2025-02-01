package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Represents a dropdown element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Dropdown extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "dropdown";

    @Getter
    private final String text;
    @Getter
    private final List<String> options;
    @Getter
    @SerializedName("default")
    private int defaultOptionIndex;

    /**
     * Create a new dropdown.
     *
     * @param text          the text of the dropdown.
     * @param options       the options of the dropdown.
     * @param defaultOption the default option of the dropdown.
     */
    public Dropdown(String text, List<String> options, int defaultOption) {
        this.text = text;
        this.options = options;
        this.defaultOptionIndex = defaultOption;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultOptionIndex = Integer.parseInt(response);
    }
}
