package org.allaymc.api.block.component.common;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.init.SimpleBlockEntityInitInfo;
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
    BlockEntityType<?> getBlockEntityType();

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

    default T getBlockEntity(Position3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    default void createBlockEntityAt(int x, int y, int z, Dimension dimension) {
        Objects.requireNonNull(dimension);
        var chunk = dimension.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z);
        }
        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            throw new IllegalStateException("Trying to create a block entity where block entity already exists! Dimension: " + dimension + " at pos " + x + ", " + y + ", " + z + "!");
        }
        var blockEntity = getBlockEntityType().createBlockEntity(SimpleBlockEntityInitInfo.builder().pos(x, y, z).dimension(dimension).build());
        chunk.addBlockEntity(blockEntity);
        if (blockEntity.sendToClient()) {
            blockEntity.sendBlockEntityDataPacketToAll();
        }
    }

    default void createBlockEntityAt(Position3ic pos) {
        createBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

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

    default void removeBlockEntityAt(Position3ic pos) {
        removeBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.dimension());
    }
}
