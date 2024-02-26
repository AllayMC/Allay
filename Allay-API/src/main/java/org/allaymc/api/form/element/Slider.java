package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class Slider extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "slider";
    @Setter
    @Getter
    private String text;
    @Setter
    @Getter
    private float min;
    @Setter
    @Getter
    private float max;
    @Setter
    @Getter
    private int step;
    @Setter
    @Getter
    @SerializedName("default")
    private float defaultValue;

    public Slider(String text, float min, float max) {
        this(text, min, max, -1);
    }

    public Slider(String text, float min, float max, int step) {
        this(text, min, max, step, -1);
    }

    public Slider(String text, float min, float max, int step, float defaultValue) {
        this.text = text;
        this.min = Math.max(min, 0f);
        this.max = Math.max(max, this.min);
        if (step > 0) this.step = step;
        if (defaultValue != -1f) this.defaultValue = defaultValue;
    }
}
