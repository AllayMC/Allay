package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player starts or stops swimming.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerToggleSwimEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSwimEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
