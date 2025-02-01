package org.allaymc.api.block.component;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

import java.util.Objects;

/**
 * @author daoge_cmd
 */
public interface BlockEntityHolderComponent<T extends BlockEntity> extends BlockComponent {
    /**
     * Get the block entity type.
     *
     * @return the block entity type.
     */
    BlockEntityType<?> getBlockEntityType();

    /**
     * Get the block entity in a specific location.
     *
     * @param x         block entity's x coordinate.
     * @param y         block entity's y coordinate.
     * @param z         block entity's z coordinate.
     * @param dimension the dimension which the block entity is in.
     *
     * @return the block entity, or null if block entity is not found.
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

    /**
     * @see #createBlockEntity(int, int, int, Dimension, boolean)
     */
    default void createBlockEntity(Position3ic pos) {
        createBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * @see #createBlockEntity(int, int, int, Dimension, boolean)
     */
    default void createBlockEntity(int x, int y, int z, Dimension dimension) {
        createBlockEntity(x, y, z, dimension, true);
    }

    /**
     * @see #createBlockEntity(int, int, int, Dimension, boolean)
     */
    default void createBlockEntity(Position3ic pos, boolean sendToClient) {
        createBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension(), sendToClient);
    }

    /**
     * Create block entity at a specific location.
     *
     * @param x            block entity's x coordinate.
     * @param y            block entity's y coordinate.
     * @param z            block entity's z coordinate.
     * @param dimension    the dimension which the block entity will be in.
     * @param sendToClient whether to send the block entity data packet to the client.
     */
    default void createBlockEntity(int x, int y, int z, Dimension dimension, boolean sendToClient) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            throw new IllegalStateException("Trying to create a block entity when block entity already exists! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z + "!");
        }
        var blockEntity = getBlockEntityType().createBlockEntity(BlockEntityInitInfo.builder().pos(x, y, z).dimension(dimension).build());
        chunk.addBlockEntity(blockEntity);
        if (sendToClient && blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToViewers();
        }
    }

    /**
     * @see #removeBlockEntity(int, int, int, Dimension)
     */
    default void removeBlockEntity(Position3ic pos) {
        removeBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * Remove a block entity in a specific location.
     *
     * @param x         block entity's x coordinate.
     * @param y         block entity's y coordinate.
     * @param z         block entity's z coordinate.
     * @param dimension the dimension which the block entity is in.
     *
     * @throws IllegalStateException if chunk isn't loaded or the block entity not exists.
     */
    default void removeBlockEntity(int x, int y, int z, Dimension dimension) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to remove a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        if (chunk.removeBlockEntity(x & 15, y, z & 15) == null) {
            throw new IllegalStateException("Trying to remove a block entity which is not exists in Dimension " + dimension + " at pos " + x + ", " + y + ", " + z + "!");
        }
    }
}
