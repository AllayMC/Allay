package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a block is ignited and catches fire.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class BlockIgniteEvent extends BlockEvent implements CancellableEvent {

    /**
     * The source block that ignite the current block,
     * can be {@code null} if the block is ignited by an entity.
     */
    protected final Block source;
    /**
     * The entity that ignite the current block,
     * can be {@code null} if the block is ignited by a block.
     */
    protected final Entity entity;
    /**
     * The cause of the block ignite.
     */
    protected final BlockIgniteCause cause;

    public BlockIgniteEvent(Block block, Block source, Entity entity, BlockIgniteCause cause) {
        super(block);
        this.source = source;
        this.entity = entity;
        this.cause = cause;
    }

    public enum BlockIgniteCause {
        EXPLOSION,
        FIREBALL,
        FLINT_AND_STEEL,
        LAVA,
        LIGHTNING,
        SPREAD
    }
}
