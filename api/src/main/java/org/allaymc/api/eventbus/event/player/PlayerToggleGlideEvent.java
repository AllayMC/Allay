package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerToggleGlideEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleGlideEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
