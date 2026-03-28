package org.allaymc.api.ddui.internal;

import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;

/**
 * DDUI property wrapper for string values.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public class StringProperty extends DataDrivenProperty<String> {

    public StringProperty(String name, String value, ObjectProperty<?> parent) {
        super(name, value, parent);
    }

    @Override
    public void acceptInput(Player player, Object data) {
        if (data instanceof String string) {
            setValue(string);
            triggerListeners(player, string);
        }
    }

    @Override
    public Object toWireValue() {
        return getValue();
    }
}
