package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code CItemUseOnBlockEvent} carries an item's block-use attempt through
 * component dispatch on the world thread. The event retains the dimension,
 * candidate placement position, and interaction information; the base component
 * returns the mutable {@code canBeUsed} result to decide whether item handling
 * consumed the interaction.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class CItemUseOnBlockEvent extends Event {
    /**
     * Dimension containing the interaction target.
     */
    protected Dimension dimension;
    /**
     * Candidate item placement block position.
     */
    protected Vector3ic placeBlockPos;
    /**
     * Player and clicked-face interaction context.
     */
    protected PlayerInteractInfo interactInfo;
    /**
     * {@code canBeUsed} holds the block-use result returned by the base component
     * when dispatch returns. Handlers set it when item handling should consume the block
     * interaction.
     */
    @Setter
    protected boolean canBeUsed;
}
