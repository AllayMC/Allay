package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * @author daoge_cmd
 */
public final class Slider extends CustomFormElement {

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

    public Slider(String text, float min, float max, int step, float defaultValue) {
        this.text = text;
        this.min = Math.max(min, 0f);
        this.max = Math.max(max, this.min);
        this.step = step;
        this.defaultValue = defaultValue;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultValue = Float.parseFloat(response);
    }
}
