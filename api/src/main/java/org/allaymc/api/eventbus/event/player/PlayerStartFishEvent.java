package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerStartFishEvent} describes a player's proposed cast of a fishing rod.
 * The rod implementation creates and configures {@code fishingHook} before dispatch;
 * cancellation prevents that implementation from adding the hook to the dimension,
 * associating it with the player, or charging cast durability.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerStartFishEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code fishingHook} retains the newly configured hook. The fishing rod adds it
     * to the dimension only after the event is accepted.
     */
    protected final EntityFishingHook fishingHook;

    /**
     * Creates a {@code PlayerStartFishEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerStartFishEvent(EntityPlayer player, EntityFishingHook fishingHook) {
        super(player);
        this.fishingHook = fishingHook;
    }
}
