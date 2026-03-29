package org.allaymc.api.ddui.internal;

import org.jetbrains.annotations.ApiStatus;

/**
 * DDUI string property for passive text rendered inside custom form layouts.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public final class DisplayTextProperty extends StringProperty {

    public DisplayTextProperty(String name, String value, ObjectProperty<?> parent) {
        super(name, value, parent);
    }

    @Override
    public boolean requiresFullRefresh() {
        var value = getValue();
        return value != null && value.indexOf('\n') >= 0;
    }
}
