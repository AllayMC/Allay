package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player punches air.
 *
 * @author daoge_cmd
 */
public class PlayerPunchAirEvent extends PlayerEvent {
    public PlayerPunchAirEvent(EntityPlayer player) {
        super(player);
    }
}
