package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockHeadBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHeadBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal());

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Check if the head should animate when placed
        var blockType = newBlockState.getBlockType();
        if (blockType != BlockTypes.DRAGON_HEAD && blockType != BlockTypes.PIGLIN_HEAD) {
            return;
        }

        if (oldBlock.isPowered()) {
            oldBlock.<BlockEntityHead>getBlockEntity().setPlayingAnimation(true);
        }
    }

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();

        // Only dragon head and piglin head respond to redstone
        var blockType = block.getBlockType();
        if (blockType != BlockTypes.DRAGON_HEAD && blockType != BlockTypes.PIGLIN_HEAD) {
            return;
        }

        var blockEntity = block.<BlockEntityHead>getBlockEntity();
        var powered = block.isPowered();
        if (blockEntity.isPlayingAnimation() != powered) {
            blockEntity.setPlayingAnimation(powered);
        }
    }
}
