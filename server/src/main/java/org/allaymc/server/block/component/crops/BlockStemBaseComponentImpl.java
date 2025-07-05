package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * @author daoge_cmd
 */
public class BlockStemBaseComponentImpl extends BlockCropsBaseComponentImpl {
    protected final BlockId fruitId;

    public BlockStemBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId fruitId) {
        super(blockType);
        this.fruitId = fruitId;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.FARMLAND) {
            current.breakBlock();
            return;
        }

        var stemFace = Objects.requireNonNull(BlockFace.fromId(current.getPropertyValue(FACING_DIRECTION)));
        if (stemFace == BlockFace.DOWN || stemFace == BlockFace.UP) {
            // No fruit block is connected to the stem,
            // so we don't need to check if the fruit block
            // is still exists
            return;
        }

        var fruitBlock = current.offsetPos(stemFace);
        if (fruitBlock.getBlockType() != fruitId.getBlockType()) {
            // Fruit block is not connected to the stem,
            // so reset the stem direction to BlockFace.DOWN
            current.getDimension().setBlockState(current.getPos(), current.setPropertyValue(FACING_DIRECTION, BlockFace.DOWN.ordinal()));
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(current) &&
            current.getDimension().getLightService().getInternalLight(current.getPos()) >= 8) {
            var growth = current.getPropertyValue(GROWTH);
            if (growth < GROWTH.getMax()) {
                var newCrop = current.setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(current, newCrop);
                if (event.call()) {
                    current.getDimension().setBlockState(current.getPos(), event.getNewBlockState());
                }
                return;
            }

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (current.offsetPos(face).getBlockType() == fruitId.getBlockType()) {
                    // Fruit block already exists
                    return;
                }
            }

            var face = BlockFace.getHorizontalBlockFaces()[ThreadLocalRandom.current().nextInt(4)];
            var fruitBlock = current.offsetPos(face);
            if (fruitBlock.getBlockType() == BlockTypes.AIR) {
                var downBlockType = fruitBlock.offsetPos(BlockFace.DOWN).getBlockType();
                if (downBlockType != BlockTypes.FARMLAND &&
                    downBlockType != BlockTypes.DIRT &&
                    downBlockType != BlockTypes.GRASS_BLOCK) {
                    return;
                }

                var event = new BlockGrowEvent(
                        new BlockStateWithPos(BlockTypes.AIR.getDefaultState(), new Position3i(fruitBlock.getPos(), current.getDimension())),
                        fruitId.getBlockType().getDefaultState()
                );
                if (event.call()) {
                    // Melon block can only be placed on farmland, dirt, or grass block
                    // Update stem direction
                    current.getDimension().setBlockState(current.getPos(), current.setPropertyValue(FACING_DIRECTION, face.ordinal()));
                    // Place melon block
                    current.getDimension().setBlockState(fruitBlock.getPos(), event.getNewBlockState());
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
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );
    }
}
