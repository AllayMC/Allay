package org.allaymc.api.form.element;

import org.jetbrains.annotations.ApiStatus;

/**
 * {@code CustomFormElement} identifies controls and decorative entries accepted by custom forms.
 * Server-settings response synchronization asks each entry to update its next serialized default;
 * decorative entries ignore that request because they have no editable default value.
 *
 * @author daoge_cmd
 */
public sealed interface CustomFormElement permits Divider, Dropdown, Header, Input, Label, Slider, StepSlider, Toggle {

    /**
     * Updates this entry's next serialized default from its positional response token when the
     * entry is editable. Server-settings forms use this operation after response callbacks;
     * decorative entries leave their existing content unchanged.
     */
    @ApiStatus.OverrideOnly
    void syncDefaultValueToResponse(String response);
}
