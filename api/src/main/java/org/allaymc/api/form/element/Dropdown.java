package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Represents a dropdown element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Dropdown implements CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "dropdown";

    @Getter
    private final String text;
    @Getter
    private final List<String> options;
    @Getter
    @SerializedName("default")
    private int defaultOptionIndex;
    @Getter
    private final String tooltip;

    /**
     * Create a new dropdown.
     *
     * @param text          the text of the dropdown
     * @param options       the options of the dropdown
     * @param defaultOption the default option of the dropdown
     * @param tooltip       the tooltip of the dropdown. Can be {@code null}
     */
    public Dropdown(String text, List<String> options, int defaultOption, String tooltip) {
        this.text = text;
        this.options = options;
        this.defaultOptionIndex = defaultOption;
        this.tooltip = tooltip;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultOptionIndex = Integer.parseInt(response);
    }
}
