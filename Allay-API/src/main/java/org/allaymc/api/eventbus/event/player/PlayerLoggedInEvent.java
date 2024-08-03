package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

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
