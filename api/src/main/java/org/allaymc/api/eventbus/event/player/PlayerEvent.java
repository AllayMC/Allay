package org.allaymc.api.eventbus.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * Represents an event related to a player. This is the base class for all player events.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class PlayerEvent extends Event {
    protected EntityPlayer player;
}
