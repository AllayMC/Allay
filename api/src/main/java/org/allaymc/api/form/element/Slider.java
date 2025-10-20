package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Represents a slider element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Slider implements CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "slider";

    @Getter
    private final String text;
    @Getter
    private final float min;
    @Getter
    private final float max;
    @Getter
    private final int step;
    @Getter
    @SerializedName("default")
    private float defaultValue;
    @Getter
    private final String tooltip;

    /**
     * Create a new slider.
     *
     * @param text         the text of the slider
     * @param min          the minimum value of the slider
     * @param max          the maximum value of the slider
     * @param step         the step of the slider
     * @param defaultValue the default value of the slider
     */
    public Slider(String text, float min, float max, int step, float defaultValue, String tooltip) {
        this.text = text;
        this.min = Math.max(min, 0f);
        this.max = Math.max(max, this.min);
        this.step = step;
        this.defaultValue = defaultValue;
        this.tooltip = tooltip;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultValue = Float.parseFloat(response);
    }
}
