package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockIgniteEvent} represents an attempt to place fire at the supplied block
 * position. The source block, responsible entity, and cause describe the ignition
 * context, while cancellation lets ignition callers stop the attempt before applying
 * the fire state.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockIgniteEvent extends BlockEvent implements CancellableEvent {

    /**
     * {@code source} identifies the source block and may be {@code null} when an
     * entity causes the ignition.
     */
    protected final Block source;
    /**
     * {@code entity} identifies the responsible entity and may be {@code null} when a
     * block causes the ignition.
     */
    protected final Entity entity;
    /**
     * Classified block ignition cause.
     */
    protected final BlockIgniteCause cause;

    /**
     * Creates an ignition event with its source context and cause. {@code source} or
     * {@code entity} may be {@code null} when that kind of initiator is absent from
     * the ignition attempt.
     */
    public BlockIgniteEvent(Block block, Block source, Entity entity, BlockIgniteCause cause) {
        super(block);
        this.source = source;
        this.entity = entity;
        this.cause = cause;
    }

    /**
     * {@code BlockIgniteCause} identifies the ignition mechanism reported by a block
     * ignition event to its listeners.
     */
    public enum BlockIgniteCause {
        /**
         * Ignition caused by explosion.
         */
        EXPLOSION,
        /**
         * Ignition caused by fireball.
         */
        FIREBALL,
        /**
         * Ignition from flint and steel.
         */
        FLINT_AND_STEEL,
        /**
         * Ignition caused by lava.
         */
        LAVA,
        /**
         * Ignition caused by lightning.
         */
        LIGHTNING,
        /**
         * Ignition spreading from fire.
         */
        SPREAD
    }
}
