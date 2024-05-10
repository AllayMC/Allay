package org.allaymc.api.eventbus.event.server.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerQuitEvent extends PlayerEvent {
    protected String reason;

    public PlayerQuitEvent(EntityPlayer player, String reason) {
        super(player);
        this.reason = reason;
    }
}
