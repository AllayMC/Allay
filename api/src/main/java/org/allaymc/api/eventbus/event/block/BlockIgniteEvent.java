package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockIgniteEvent extends BlockEvent implements CancellableEvent {

    /**
     * The source block that ignite the current block,
     * can be {@code null} if the block is ignited by an entity.
     */
    protected final BlockStateWithPos source;
    /**
     * The entity that ignite the current block,
     * can be {@code null} if the block is ignited by a block.
     */
    protected final Entity entity;
    /**
     * The cause of the block ignite.
     */
    protected final BlockIgniteCause cause;

    public BlockIgniteEvent(BlockStateWithPos blockState, BlockStateWithPos source, Entity entity, BlockIgniteCause cause) {
        super(blockState);
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
