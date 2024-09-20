package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class PlayerCommandEvent extends PlayerEvent {
    protected String command;

    public PlayerCommandEvent(EntityPlayer player, String command) {
        super(player);
        this.command = command;
    }
}
