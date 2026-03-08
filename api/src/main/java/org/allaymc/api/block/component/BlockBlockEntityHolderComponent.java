package org.allaymc.api.block.component;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

/**
 * @author daoge_cmd
 */
public interface BlockBlockEntityHolderComponent<T extends BlockEntity> extends BlockComponent {
    /**
     * Get the block entity type.
     *
     * @return the block entity type
     */
    BlockEntityType<?> getBlockEntityType();

    /**
     * @see #getBlockEntity(int, int, int, Dimension)
     */
    default T getBlockEntity(Position3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * Get the block entity in a specific location. If the block entity is not present, it will be created.
     *
     * @param x         block entity's x coordinate
     * @param y         block entity's y coordinate
     * @param z         block entity's z coordinate
     * @param dimension the dimension which the block entity is in
     * @return the block entity in the specified location, will never be {@code null}
     * @throws IllegalStateException if the block entity is not the correct type
     */
    T getBlockEntity(int x, int y, int z, Dimension dimension);
}
