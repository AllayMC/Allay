package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.NETWORK)
public class PlayerJoinEvent extends PlayerEvent implements CancellableEvent {
    public PlayerJoinEvent(EntityPlayer player) {
        super(player);
    }
}
