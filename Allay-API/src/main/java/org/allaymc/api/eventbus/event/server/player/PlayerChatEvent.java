package org.allaymc.api.eventbus.event.server.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class PlayerChatEvent extends PlayerEvent implements CancellableEvent {
    protected String message;

    public PlayerChatEvent(EntityPlayer player, String message) {
        super(player);
        this.message = message;
    }
}
