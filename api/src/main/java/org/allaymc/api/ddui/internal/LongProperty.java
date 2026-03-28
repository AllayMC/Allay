package org.allaymc.api.ddui.internal;

import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;

/**
 * DDUI property wrapper for long values.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public class LongProperty extends DataDrivenProperty<Long> {

    public LongProperty(String name, long value, ObjectProperty<?> parent) {
        super(name, value, parent);
    }

    @Override
    public void acceptInput(Player player, Object data) {
        if (data instanceof Number number) {
            var value = number.longValue();
            setValue(value);
            triggerListeners(player, value);
        }
    }

    @Override
    public Object toWireValue() {
        return getValue();
    }
}
