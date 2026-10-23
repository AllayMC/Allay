package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * {@code StepSlider} represents a custom-form slider whose positions are strings in a step list.
 * Construction retains the list and default index without checking their consistency;
 * server-settings synchronization parses a returned index for the next serialized default.
 *
 * @author daoge_cmd
 */
public final class StepSlider implements CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "step_slider";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;
    /**
     * {@code steps} retains the caller's mutable list; its generated getter exposes the same list,
     * and later serialization observes list changes.
     */
    @Getter
    private final List<String> steps;
    /**
     * Zero-based initially selected step index.
     */
    @Getter
    @SerializedName("default")
    private int defaultStepIndex;
    /**
     * Optional control hover text.
     */
    @Getter
    private final String tooltip;

    /**
     * Creates a step slider retaining the supplied list and selected index. The constructor does
     * not copy the list or validate the index, and a {@code null} tooltip is allowed.
     */
    public StepSlider(String text, List<String> steps, int defaultStep, String tooltip) {
        this.text = text;
        this.steps = steps;
        this.defaultStepIndex = defaultStep;
        this.tooltip = tooltip;
    }

    /**
     * Parses the response as an integer and stores it as the next default index. Parsing failure
     * leaves the old default unchanged; successful parsing does not validate the index against the
     * option list.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultStepIndex = Integer.parseInt(response);
    }
}
