package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.player.Player;

/**
 * Internal DDUI property representing a button click trigger.
 *
 * @author Miroshka
 */
final class ButtonClickProperty extends LongProperty {

    ButtonClickProperty(ObjectProperty<?> parent) {
        super("onClick", 0L, parent);
    }

    @Override
    public void acceptInput(Player player, Object data) {
        if (data instanceof Number number) {
            var value = number.longValue();
            setValue(value);
            triggerListeners(player, value);
        }
    }
}
