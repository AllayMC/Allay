package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player jumps.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class PlayerJumpEvent extends PlayerEvent {
    public PlayerJumpEvent(EntityPlayer player) {
        super(player);
    }
}
