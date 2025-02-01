package org.allaymc.api.form.element;

import org.jetbrains.annotations.ApiStatus;

/**
 * The base class for elements that can be used in {@link org.allaymc.api.form.type.CustomForm}
 *
 * @author daoge_cmd
 */
public abstract sealed class CustomFormElement permits
        Dropdown, Input, Label, Slider, StepSlider, Toggle {

    /**
     * Only used for server settings form
     */
    @ApiStatus.OverrideOnly
    public abstract void syncDefaultValueToResponse(String response);
}
