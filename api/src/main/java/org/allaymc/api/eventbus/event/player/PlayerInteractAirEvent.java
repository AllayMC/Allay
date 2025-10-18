package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Event triggered when a player interacts with air (right-click in air).
 *
 * @author galyckyj
 */
@Getter
public class PlayerInteractAirEvent extends PlayerEvent implements CancellableEvent {

    public PlayerInteractAirEvent(EntityPlayer player) {
        super(player);
    }
}
