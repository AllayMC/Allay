package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CItemRightClickInAirEvent} notifies item components that a player
 * activated an immediate-use item in air. The event retains the player reference
 * for capability-specific actions, and the base item component dispatches it
 * without collecting a boolean handled result.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CItemRightClickInAirEvent extends Event {
    /**
     * Player activating the item in air.
     */
    protected EntityPlayer player;
}
