package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.RedstoneHelper;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockHeadBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityHead> blockEntityHolderComponent;

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

        var pos = oldBlock.getPosition();
        if (RedstoneHelper.isPoweredAt(pos)) {
            blockEntityHolderComponent.getBlockEntity(pos).setPlayingAnimation(true);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Only dragon head and piglin head respond to redstone
        var blockType = block.getBlockType();
        if (blockType != BlockTypes.DRAGON_HEAD && blockType != BlockTypes.PIGLIN_HEAD) {
            return;
        }

        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        boolean powered = RedstoneHelper.isPoweredAt(block.getPosition());
        if (blockEntity.isPlayingAnimation() != powered) {
            blockEntity.setPlayingAnimation(powered);
        }
    }
}
