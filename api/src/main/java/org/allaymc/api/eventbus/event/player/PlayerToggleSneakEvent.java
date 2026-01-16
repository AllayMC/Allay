package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player starts or stops sneaking.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerToggleSneakEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSneakEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
