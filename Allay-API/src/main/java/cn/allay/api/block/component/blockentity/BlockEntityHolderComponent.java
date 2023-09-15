package cn.allay.api.block.component.blockentity;

import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.block.data.BlockPos;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.init.SimpleBlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.math.position.Position3ic;
import cn.allay.api.world.World;

import java.util.Objects;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityHolderComponent extends BlockComponent {
    BlockEntityType<?> getBlockEntityType();

    default void createBlockEntityAt(int x, int y, int z, World world) {
        Objects.requireNonNull(world);
        var chunk = world.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to create a block entity in an unload chunk! World: " + world.getName() + " at pos " + x + ", " + y + ", " + z);
        }
        var presentBlockEntity = chunk.getBlockEntity(x & 15, y, z & 15);
        if (presentBlockEntity != null) {
            throw new IllegalStateException("Trying to create a block entity in world " + world.getName() + " at pos " + x + ", " + y + ", " + z + "!");
        }
        var blockEntity = getBlockEntityType().createBlockEntity(new SimpleBlockEntityInitInfo<>(new Position3i(x, y, z, world)));
        chunk.addBlockEntity(blockEntity);
    }

    default void createBlockEntityAt(Position3ic pos) {
        createBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.world());
    }

    default void removeBlockEntityAt(int x, int y, int z, World world) {
        Objects.requireNonNull(world);
        var chunk = world.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            throw new IllegalStateException("Trying to remove a block entity in an unload chunk! World: " + world.getName() + " at pos " + x + ", " + y + ", " + z);
        }
        if (chunk.removeBlockEntity(x & 15, y, z & 15) == null) {
            throw new IllegalStateException("Trying to remove a block entity which is not exists in world " + world.getName() + " at pos " + x + ", " + y + ", " + z + "!");
        }
    }

    default void removeBlockEntityAt(Position3ic pos) {
        removeBlockEntityAt(pos.x(), pos.y(), pos.z(), pos.world());
    }
}
