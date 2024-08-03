package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerKickEvent extends PlayerEvent implements CancellableEvent {

    protected String reason;

    public PlayerKickEvent(EntityPlayer player, String reason) {
        super(player);
        this.reason = reason;
    }
}
