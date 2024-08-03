package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerCommandEvent extends PlayerEvent {
    protected String command;

    public PlayerCommandEvent(EntityPlayer player, String command) {
        super(player);
        this.command = command;
    }
}
