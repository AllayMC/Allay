package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Allay Project 2024/2/26
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
