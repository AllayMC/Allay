package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player punches air.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerPunchAirEvent extends PlayerEvent implements CancellableEvent {
    public PlayerPunchAirEvent(EntityPlayer player) {
        super(player);
    }
}
