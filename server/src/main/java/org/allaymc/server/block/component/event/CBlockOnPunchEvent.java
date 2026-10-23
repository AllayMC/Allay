package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

/**
 * {@code CBlockOnPunchEvent} carries a punched block and face to companion block
 * components. The item and entity describe optional punch attribution, allowing the
 * same internal event to serve player punches and callers without a tool or
 * responsible entity.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class CBlockOnPunchEvent extends Event {
    /**
     * Snapshot of punched block.
     */
    private final Block currentBlock;
    /**
     * Face receiving the punch.
     */
    private final BlockFace blockFace;
    /**
     * {@code usedItem} identifies the punch tool and may be {@code null} when the
     * caller supplies no item.
     */
    private final ItemStack usedItem;
    /**
     * {@code entity} identifies the punch initiator and may be {@code null} when the
     * caller supplies no entity.
     */
    private final Entity entity;
}
