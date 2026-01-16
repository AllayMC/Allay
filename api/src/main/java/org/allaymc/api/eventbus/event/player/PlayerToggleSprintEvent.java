package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player starts or stops sprinting.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerToggleSprintEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSprintEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
