package org.allaymc.api.eventbus.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code PlayerEvent} is the common base for events associated with a player entity.
 * The stored {@code player} may be an entity without a network controller; concrete
 * subclasses define whether their action is cancellable and which thread dispatches
 * the event.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class PlayerEvent extends Event {
    /**
     * {@code player} identifies the entity associated with this event. The event
     * retains this reference and does not require a network controller.
     */
    protected EntityPlayer player;
}
