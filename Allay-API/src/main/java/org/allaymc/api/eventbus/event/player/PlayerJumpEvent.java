package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class PlayerJumpEvent extends PlayerEvent {
    public PlayerJumpEvent(EntityPlayer player) {
        super(player);
    }
}
