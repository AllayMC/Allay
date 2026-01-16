package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player used an item in air.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class PlayerUseItemInAirEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The time the player has been using the item in air in game ticks.
     */
    protected long usingTime;

    public PlayerUseItemInAirEvent(EntityPlayer player, long usingTime) {
        super(player);
        this.usingTime = usingTime;
    }
}
