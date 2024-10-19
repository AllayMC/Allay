package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.math.location.Location3fc;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerMoveEvent extends PlayerEvent implements CancellableEvent {
    protected Location3fc from;
    protected Location3fc to;

    public PlayerMoveEvent(EntityPlayer player, Location3fc from, Location3fc to) {
        super(player);
        this.from = from;
        this.to = to;
    }
}
