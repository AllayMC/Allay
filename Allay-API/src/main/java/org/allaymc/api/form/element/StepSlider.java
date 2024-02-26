package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public final class StepSlider extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "step_slider";
    @Getter
    private final String text;
    @Getter
    private final List<String> steps;
    @Getter
    @SerializedName("default")
    private final int defaultStepIndex;

    public StepSlider(String text, List<String> steps, int defaultStep) {
        this.text = text;
        this.steps = steps;
        this.defaultStepIndex = defaultStep;
    }
}
