package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code BlockBreakEvent} represents a proposed block break together with its tool
 * and responsible entity. The dimension checks cancellation before producing break
 * effects and invoking block drop handling; either context value may be absent for
 * breaks without a tool or entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class BlockBreakEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code usedItem} identifies the tool used for the break and may be {@code null}
     * when no tool is supplied.
     */
    protected ItemStack usedItem;
    /**
     * {@code entity} identifies the entity responsible for the break and may be
     * {@code null} for unattributed changes.
     */
    protected Entity entity;

    /**
     * Creates a break event with the supplied block, tool, and entity.
     * {@code usedItem} and {@code entity} may be {@code null}, and construction only
     * records the context for later dispatch.
     */
    public BlockBreakEvent(Block block, ItemStack usedItem, Entity entity) {
        super(block);
        this.usedItem = usedItem;
        this.entity = entity;
    }
}
