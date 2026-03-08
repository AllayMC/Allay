package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player tries to throw a fishing rod.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerStartFishEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The fishing hook entity that will be spawned.
     */
    protected final EntityFishingHook fishingHook;

    public PlayerStartFishEvent(EntityPlayer player, EntityFishingHook fishingHook) {
        super(player);
        this.fishingHook = fishingHook;
    }
}
