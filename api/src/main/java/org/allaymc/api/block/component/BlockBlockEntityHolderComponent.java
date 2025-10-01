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
     * Get the block entity in a specific location.
     *
     * @param x         block entity's x coordinate
     * @param y         block entity's y coordinate
     * @param z         block entity's z coordinate
     * @param dimension the dimension which the block entity is in
     * @return the block entity, or null if block entity is not found
     */
    default T getBlockEntity(int x, int y, int z, Dimension dimension) {
        var blockEntity = dimension.getBlockEntity(x, y, z);
        if (blockEntity == null) return null;
        if (blockEntity.getBlockEntityType() != this.getBlockEntityType()) {
            throw new IllegalStateException(
                    "Mismatched block entity type at pos %d, %d, %d, %s! Expected: %s, actual: %s"
                            .formatted(
                                    x, y, z, dimension.toString(),
                                    this.getBlockEntityType().getName(), blockEntity.getBlockEntityType().getName()
                            )
            );
        }
        return (T) blockEntity;
    }

    /**
     * @see #getBlockEntity(int, int, int, Dimension)
     */
    default T getBlockEntity(Position3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

}
