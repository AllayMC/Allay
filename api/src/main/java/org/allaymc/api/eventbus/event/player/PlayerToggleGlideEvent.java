package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player starts or stops gliding with elytra.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerToggleGlideEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleGlideEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
