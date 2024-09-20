package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
public class PlayerJumpEvent extends PlayerEvent {
    public PlayerJumpEvent(EntityPlayer player) {
        super(player);
    }
}
