package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockMelonStemBaseComponentImpl extends BlockCropsBaseComponentImpl {

    protected final BlockId fruitId;

    public BlockMelonStemBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId fruitId) {
        super(blockType);
        this.fruitId = fruitId;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (!canKeepExisting(current, neighbor, face)) {
            current.pos().dimension().breakBlock(current.pos(), null, null);
        } else {
            var stemFace = BlockFace.fromId(current.blockState().getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
            if (stemFace == BlockFace.DOWN || stemFace == BlockFace.UP) {
                // No melon block is connected to the stem,
                // so we don't need to check if the melon block
                // is still exists
                return;
            }

            var melonBlockPos = stemFace.offsetPos(current.pos());
            if (current.dimension().getBlockState(melonBlockPos).getBlockType() != fruitId.getBlockType()) {
                // Melon block is not connected to the stem,
                // so reset the stem direction to BlockFace.DOWN
                current.dimension().setBlockState(current.pos(), current.blockState().setProperty(BlockPropertyTypes.FACING_DIRECTION, BlockFace.DOWN.ordinal()));
            }
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(current) &&
            current.dimension().getLightService().getInternalLight(current.pos()) >= 8) {
            var growth = current.blockState().getPropertyValue(BlockPropertyTypes.GROWTH);
            if (growth < 7) {
                var newCrop = current.blockState().setProperty(BlockPropertyTypes.GROWTH, growth + 1);
                var event = new BlockGrowEvent(current, newCrop);
                if (event.call()) {
                    current.dimension().setBlockState(current.pos(), event.getNewBlockState());
                }
                return;
            }

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (current.offsetPos(face).blockState().getBlockType() == fruitId.getBlockType()) {
                    // Melon block already exists
                    return;
                }
            }

            var face = BlockFace.getHorizontalBlockFaces()[ThreadLocalRandom.current().nextInt(4)];
            var melonBlockPos = face.offsetPos(current.pos());
            if (current.dimension().getBlockState(melonBlockPos).getBlockType() == BlockTypes.AIR) {
                var downBlockType = current.dimension().getBlockState(BlockFace.DOWN.offsetPos(melonBlockPos)).getBlockType();
                if (downBlockType != BlockTypes.FARMLAND &&
                    downBlockType != BlockTypes.DIRT &&
                    downBlockType != BlockTypes.GRASS_BLOCK) {
                    return;
                }

                var event = new BlockGrowEvent(
                        new BlockStateWithPos(BlockTypes.AIR.getDefaultState(), new Position3i(melonBlockPos, current.dimension())),
                        fruitId.getBlockType().getDefaultState()
                );
                if (event.call()) {
                    // Melon block can only be placed on farmland, dirt, or grass block
                    // Update stem direction
                    current.dimension().setBlockState(current.pos(), current.blockState().setProperty(BlockPropertyTypes.FACING_DIRECTION, face.ordinal()));
                    // Place melon block
                    current.dimension().setBlockState(melonBlockPos, event.getNewBlockState());
                }
            }
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP ||
            placementInfo.getClickedBlockState().getBlockType() != BlockTypes.FARMLAND) {
            return false;
        }

        // Do not use processBlockProperties here, because the FACING_DIRECTION property should keep
        // the default value (BlockFace.DOWN) rather than facing to the player
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );
        return true;
    }
}
