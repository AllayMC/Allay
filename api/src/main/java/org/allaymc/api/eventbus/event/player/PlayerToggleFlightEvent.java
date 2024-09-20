package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerToggleFlightEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleFlightEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
