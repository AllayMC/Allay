package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockFallableBaseComponent;
import org.allaymc.api.block.component.common.BlockLiquidComponent;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.block.BlockFallEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * Allay Project 27/06/2024
 *
 * @author IWareQ
 */
public class BlockFallableBaseComponentImpl extends BlockBaseComponentImpl implements BlockFallableBaseComponent {
    public BlockFallableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        var pos = current.pos();
        var dimension = pos.dimension();
        trySpawnFallingEntity(dimension, pos, current.blockState());
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (trySpawnFallingEntity(dimension, placeBlockPos, blockState)) return true;
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    protected boolean trySpawnFallingEntity(Dimension dimension, Vector3ic pos, BlockState blockState) {
        var down0 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        var down1 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos), 1).getBlockType();
        if (invalidDownBlock(down0, down1)) {
            var event = new BlockFallEvent(new BlockStateWithPos(blockState, new Position3i(pos, dimension), 0));
            event.call();
            if (event.isCancelled()) {
                return false;
            }

            dimension.getEntityService().addEntity(createFallingEntity(dimension, pos, blockState), () -> {
                dimension.setBlockState(
                        pos.x(), pos.y(), pos.z(),
                        BlockTypes.AIR.getDefaultState()
                );
            });
            return true;
        }

        return false;
    }

    protected boolean invalidDownBlock(BlockType<?> down0, BlockType<?> down1) {
        return down0 == BlockTypes.AIR ||
               down0 == BlockTypes.FIRE ||
               down0.getBlockBehavior() instanceof BlockLiquidComponent ||
               (down0 == BlockTypes.BUBBLE_COLUMN && down1.getBlockBehavior() instanceof BlockLiquidComponent);
    }

    protected EntityFallingBlock createFallingEntity(Dimension dimension, Vector3ic pos, BlockState blockState) {
        return EntityTypes.FALLING_BLOCK.createEntity(
                SimpleEntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos.x() + 0.5f, pos.y(), pos.z() + 0.5f)
                        .nbt(NbtMap.builder()
                                .putInt("BlockStateHash", blockState.blockStateHash())
                                .build()
                        )
                        .build()
        );
    }
}
