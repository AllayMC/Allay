package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
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
