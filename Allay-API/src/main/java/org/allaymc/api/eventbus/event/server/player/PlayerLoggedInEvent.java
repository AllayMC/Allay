package org.allaymc.api.eventbus.event.server.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class PlayerLoggedInEvent extends PlayerEvent implements CancellableEvent {
    public PlayerLoggedInEvent(EntityPlayer player) {
        super(player);
    }
}
