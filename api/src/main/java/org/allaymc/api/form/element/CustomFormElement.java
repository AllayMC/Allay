package org.allaymc.api.form.element;

import org.jetbrains.annotations.ApiStatus;

/**
 * The base class for elements that can be used in {@link org.allaymc.api.form.type.CustomForm}
 *
 * @author daoge_cmd
 */
public sealed interface CustomFormElement permits Divider, Dropdown, Header, Input, Label, Slider, StepSlider, Toggle {

    /**
     * Only used for server settings form
     */
    @ApiStatus.OverrideOnly
    void syncDefaultValueToResponse(String response);
}
