package org.allaymc.api.form.element;

/**
 * @author daoge_cmd
 */
public abstract sealed class CustomFormElement permits
        Dropdown, Input, Label, Slider, StepSlider, Toggle {

    /**
     * Only used for server settings form
     */
    public abstract void syncDefaultValueToResponse(String response);
}
