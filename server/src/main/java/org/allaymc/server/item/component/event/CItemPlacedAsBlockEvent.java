package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code CItemPlacedAsBlockEvent} reports a successful block placement to item
 * components after the shared helper applies block-entity data and player
 * consumption. The event retains the dimension, placement position, and item
 * stack references; the stack can already reflect the consumed count.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemPlacedAsBlockEvent extends Event {
    /**
     * Dimension receiving the placed block.
     */
    protected Dimension dimension;
    /**
     * Position of the placed block.
     */
    protected Vector3ic placeBlockPos;
    /**
     * {@code itemStack} retains the placing stack after consumption processing.
     * Handlers observe its current mutable state rather than a snapshot of the
     * pre-placement count.
     */
    protected ItemStack itemStack;
}
