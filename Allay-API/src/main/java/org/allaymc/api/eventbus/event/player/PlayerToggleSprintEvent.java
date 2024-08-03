package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerToggleSprintEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSprintEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
