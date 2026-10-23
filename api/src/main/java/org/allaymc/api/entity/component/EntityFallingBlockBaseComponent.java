package org.allaymc.api.entity.component;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code EntityFallingBlockBaseComponent} exposes the block state represented
 * by a falling block entity. The state supplies the eventual placed block or
 * dropped item, and the entity combines this contract with physics while the
 * block travels through the world.
 *
 * @author IWareQ
 */
public interface EntityFallingBlockBaseComponent extends EntityBaseComponent {
    /**
     * Returns the block state represented by this falling block entity.
     */
    BlockState getBlockState();

    /**
     * Stores the block state used when this falling block lands. Callers must
     * supply a usable state before landing or serialization, because those
     * paths access its behavior and saved state hash.
     */
    void setBlockState(BlockState blockState);
}
