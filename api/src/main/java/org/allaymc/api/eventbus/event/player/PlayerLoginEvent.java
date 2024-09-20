package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
public class PlayerLoginEvent extends PlayerEvent implements CancellableEvent {
    public PlayerLoginEvent(EntityPlayer player) {
        super(player);
    }
}
