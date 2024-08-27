package org.allaymc.api.block.component;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.initinfo.SimpleBlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

import java.util.Objects;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityHolderComponent<T extends BlockEntity> extends BlockComponent {
    /**
     * Get the block entity type
     *
     * @return the block entity type
     */
    BlockEntityType<?> getBlockEntityType();

    /**
     * Get the block entity in a specific location
     *
     * @param x         block entity's x coordinate
     * @param y         block entity's y coordinate
     * @param z         block entity's z coordinate
     * @param dimension the dimension which the block entity is in
     *
     * @return the block entity, or null if block entity is not found
     */
    default T getBlockEntityAt(int x, int y, int z, Dimension dimension) {
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

    default T getBlockEntityAt(Position3ic pos) {
        return getBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    default void createBlockEntityAt(Position3ic pos) {
        createBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    default void createBlockEntityAt(int x, int y, int z, Dimension dimension) {
        createBlockEntityAt(x, y, z, dimension, true);
    }

    default void createBlockEntityAt(Position3ic pos, boolean sendToClient) {
        createBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension(), sendToClient);
    }

    /**
     * Create block entity at a specific location
     *
     * @param x            block entity's x coordinate
     * @param y            block entity's y coordinate
     * @param z            block entity's z coordinate
     * @param dimension    the dimension which the block entity will be in
     * @param sendToClient whether to send the block entity creating packet to the client
     */
    default void createBlockEntityAt(int x, int y, int z, Dimension dimension, boolean sendToClient) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            throw new IllegalStateException("Trying to create a block entity when block entity already exists! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z + "!");
        }
        var blockEntity = getBlockEntityType().createBlockEntity(SimpleBlockEntityInitInfo.builder().pos(x, y, z).dimension(dimension).build());
        chunk.addBlockEntity(blockEntity);
        if (sendToClient && blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToViewers();
        }
    }

    default void removeBlockEntityAt(Position3ic pos) {
        removeBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * Remove a block entity in a specific location
     *
     * @param x         block entity's x coordinate
     * @param y         block entity's y coordinate
     * @param z         block entity's z coordinate
     * @param dimension the dimension which the block entity is in
     *
     * @throws IllegalStateException if chunk isn't loaded or the block entity not exists
     */
    default void removeBlockEntityAt(int x, int y, int z, Dimension dimension) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to remove a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        if (chunk.removeBlockEntity(x & 15, y, z & 15) == null) {
            throw new IllegalStateException("Trying to remove a block entity which is not exists in Dimension " + dimension + " at pos " + x + ", " + y + ", " + z + "!");
        }
    }
}
