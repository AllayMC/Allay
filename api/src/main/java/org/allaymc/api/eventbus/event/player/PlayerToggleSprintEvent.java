package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerToggleSprintEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSprintEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
