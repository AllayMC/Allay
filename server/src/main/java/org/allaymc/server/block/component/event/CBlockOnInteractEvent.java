package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;

/**
 * {@code CBlockOnInteractEvent} carries item interaction context through a block
 * behavior's component handlers. The shared mutable {@code success} flag reports
 * whether a handler accepted the interaction, and the base component reads that flag
 * after dispatch to choose its result.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class CBlockOnInteractEvent extends Event {
    /**
     * Item offered to block.
     */
    private final ItemStack itemStack;
    /**
     * Dimension containing the interaction.
     */
    private final Dimension dimension;
    /**
     * {@code interactInfo} supplies the clicked position and player context, and its
     * presence depends on the caller of the block interaction hook.
     */
    private final PlayerInteractInfo interactInfo;
    /**
     * {@code success} is initialized to {@code false} by the base interaction
     * dispatcher. Component handlers can replace it before the dispatcher returns its
     * current value.
     */
    @Setter
    private boolean success;
}
