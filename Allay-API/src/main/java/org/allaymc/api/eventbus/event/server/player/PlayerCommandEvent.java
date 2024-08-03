package org.allaymc.api.eventbus.event.server.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class PlayerCommandEvent extends PlayerEvent {

    protected String command;

    public PlayerCommandEvent(EntityPlayer player, String command) {
        super(player);
        this.command = command;
    }
}
