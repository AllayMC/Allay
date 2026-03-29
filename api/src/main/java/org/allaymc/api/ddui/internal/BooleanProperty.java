package org.allaymc.api.ddui.internal;

import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;

/**
 * DDUI property wrapper for boolean values.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public class BooleanProperty extends DataDrivenProperty<Boolean> {

    public BooleanProperty(String name, boolean value, ObjectProperty<?> parent) {
        super(name, value, parent);
    }

    @Override
    public void acceptInput(Player player, Object data) {
        if (!(data instanceof Boolean bool)) {
            return;
        }

        setValue(bool);
        triggerListeners(player, bool);
    }

    @Override
    public Object toWireValue() {
        return getValue();
    }
}
