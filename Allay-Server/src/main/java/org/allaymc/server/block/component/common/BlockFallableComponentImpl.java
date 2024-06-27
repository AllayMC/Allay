package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockFallableComponent;
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
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * Allay Project 27/06/2024
 *
 * @author IWareQ
 */
public class BlockFallableComponentImpl extends BlockBaseComponentImpl implements BlockFallableComponent {
    public BlockFallableComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face); // TODO
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var down0 = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos)).getBlockType();
        var down1 = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos), 1).getBlockType();
        if (invalidDownBlock(down0, down1)) {
            dimension.getEntityService().addEntity(createFallingEntity(dimension, placeBlockPos, blockState), () -> {
                dimension.setBlockState(
                        placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), BlockTypes.AIR_TYPE.getDefaultState(),
                        0, true, true
                );
            });
            return true;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    protected boolean invalidDownBlock(BlockType<?> down0, BlockType<?> down1) {
        return down0 == BlockTypes.AIR_TYPE ||
               down0 == BlockTypes.FIRE_TYPE ||
               down0.getBlockBehavior() instanceof BlockLiquidComponent ||
               (down0 == BlockTypes.BUBBLE_COLUMN_TYPE && down1.getBlockBehavior() instanceof BlockLiquidComponent);
    }

    protected EntityFallingBlock createFallingEntity(Dimension dimension, Vector3ic pos, BlockState blockState) {
        return EntityTypes.FALLING_BLOCK_TYPE.createEntity(
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
