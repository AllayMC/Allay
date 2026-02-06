package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * @author daoge_cmd | Dhaiven
 */
public class BlockBigFlowerBaseComponentImpl extends BlockSmallFlowerBaseComponentImpl {

    public BlockBigFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo) &&
               dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, true), placementInfo);
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();
        var keep = true;
        if (face == BlockFace.UP) {
            if (!block.getPropertyValue(UPPER_BLOCK_BIT)) {
                keep = isSamePlant(neighbor.getBlockState());
            }
        } else if (face == BlockFace.DOWN) {
            if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
                keep = isSamePlant(neighbor.getBlockState());
            } else {
                keep = canPlaceOn(neighbor.getBlockState().getBlockType());
            }
        }

        if (!keep) {
            block.breakBlock();
        }
    }

    protected boolean isSamePlant(BlockState otherBlock) {
        return otherBlock.getBlockType() == blockType;
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        return !block.getPropertyValue(UPPER_BLOCK_BIT);
    }
}
