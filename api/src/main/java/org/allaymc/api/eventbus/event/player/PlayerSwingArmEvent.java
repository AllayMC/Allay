package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player sends arm swing animation packet.
 *
 * @author zernix2077
 */
@CallerThread(ThreadType.NETWORK)
public class PlayerSwingArmEvent extends PlayerEvent {
    public PlayerSwingArmEvent(EntityPlayer player) {
        super(player);
    }
}
