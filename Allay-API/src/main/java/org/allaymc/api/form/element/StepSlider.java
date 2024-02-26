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
public class StepSlider extends CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "step_slider";
    @Setter
    @Getter
    private String text;
    @Getter
    private final List<String> steps;
    @Getter
    @SerializedName("default")
    private int defaultStepIndex;

    public StepSlider(String text) {
        this(text, new ArrayList<>());
    }

    public StepSlider(String text, List<String> steps) {
        this(text, steps, 0);
    }

    public StepSlider(String text, List<String> steps, int defaultStep) {
        this.text = text;
        this.steps = steps;
        this.defaultStepIndex = defaultStep;
    }

    public void setDefaultOptionIndex(int index) {
        if (index >= steps.size()) return;
        this.defaultStepIndex = index;
    }

    public void addStep(String step) {
        addStep(step, false);
    }

    public void addStep(String step, boolean isDefault) {
        steps.add(step);
        if (isDefault) this.defaultStepIndex = steps.size() - 1;
    }
}
