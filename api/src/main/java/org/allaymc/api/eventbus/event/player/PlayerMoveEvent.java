package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.math.location.Location3dc;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerMoveEvent extends PlayerEvent implements CancellableEvent {
    protected Location3dc from;
    protected Location3dc to;

    public PlayerMoveEvent(EntityPlayer player, Location3dc from, Location3dc to) {
        super(player);
        this.from = from;
        this.to = to;
    }
}
