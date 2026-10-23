package org.allaymc.api.block.component;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

/**
 * {@code BlockBlockEntityHolderComponent} exposes the block entity type associated
 * with a block and retrieves its entity by world position. The server implementation
 * creates missing entities in loaded chunks and replaces entities whose type differs
 * from the holder's configured type.
 *
 * @param <T> block entity interface returned by this component
 *
 * @author daoge_cmd
 */
public interface BlockBlockEntityHolderComponent<T extends BlockEntity> extends BlockComponent {
    /**
     * Returns the block entity type managed by this holder.
     */
    BlockEntityType<?> getBlockEntityType();

    /**
     * Returns the block entity at the position and its dimension, creating it when
     * absent. The coordinate overload defines loaded-chunk requirements and any
     * recovery needed for an incompatible stored entity.
     */
    default T getBlockEntity(Position3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * Returns the block entity at these world block coordinates, creating it when
     * absent. The server implementation requires a loaded chunk and replaces a stored
     * entity with the wrong type.
     *
     * @throws IllegalStateException if creation or removal requires an unloaded chunk, or creation finds an existing entity
     */
    T getBlockEntity(int x, int y, int z, Dimension dimension);
}
