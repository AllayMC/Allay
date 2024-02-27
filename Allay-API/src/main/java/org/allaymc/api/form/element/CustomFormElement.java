package org.allaymc.api.form.element;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public abstract sealed class CustomFormElement permits
        Dropdown, Input, Label, Slider, StepSlider, Toggle {

    // Only used for server settings form
    public abstract void syncDefaultValueToResponse(String response);
}
