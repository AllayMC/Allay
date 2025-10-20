package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Represents a step slider element in a {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class StepSlider implements CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "step_slider";

    @Getter
    private final String text;
    @Getter
    private final List<String> steps;
    @Getter
    @SerializedName("default")
    private int defaultStepIndex;
    @Getter
    private final String tooltip;

    /**
     * Create a new step slider.
     *
     * @param text        the text of the step slider
     * @param steps       the steps of the step slider
     * @param defaultStep the default step of the step slider
     */
    public StepSlider(String text, List<String> steps, int defaultStep, String tooltip) {
        this.text = text;
        this.steps = steps;
        this.defaultStepIndex = defaultStep;
        this.tooltip = tooltip;
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultStepIndex = Integer.parseInt(response);
    }
}
