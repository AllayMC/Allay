package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
public class PlayerRespawnEvent extends PlayerEvent {
    public PlayerRespawnEvent(EntityPlayer player) {
        super(player);
    }
}
