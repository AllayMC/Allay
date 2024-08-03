package org.allaymc.api.eventbus.event.world.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;
import org.allaymc.api.math.location.Location3fc;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
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
