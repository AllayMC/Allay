package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player starts using an item in air.
 *
 * @author daoge_cmd
 */
public class PlayerStartUseItemInAirEvent extends PlayerEvent implements CancellableEvent {
    public PlayerStartUseItemInAirEvent(EntityPlayer player) {
        super(player);
    }
}
