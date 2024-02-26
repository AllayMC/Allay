package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class Dropdown extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "dropdown";
    @Setter
    @Getter
    private String text;
    @Getter
    private final List<String> options;
    @Getter
    @SerializedName("default")
    private int defaultOptionIndex;

    public Dropdown(String text) {
        this(text, new ArrayList<>());
    }

    public Dropdown(String text, List<String> options) {
        this(text, options, 0);
    }

    public Dropdown(String text, List<String> options, int defaultOption) {
        this.text = text;
        this.options = options;
        this.defaultOptionIndex = defaultOption;
    }

    public void setDefaultOptionIndex(int index) {
        if (index >= options.size()) return;
        this.defaultOptionIndex = index;
    }

    public void addOption(String option) {
        addOption(option, false);
    }

    public void addOption(String option, boolean isDefault) {
        options.add(option);
        if (isDefault) this.defaultOptionIndex = options.size() - 1;
    }
}
