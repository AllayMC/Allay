package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockFrameBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityItemFrame> blockEntityHolderComponent;

    public BlockFrameBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        if (face.opposite() != BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION))) {
            return;
        }

        if (!neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        if (!placementInfo.getClickedBlock().getBlockStateData().isSolid()) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal()));
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var itemFrame = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (itemFrame == null) {
            return 0;
        }
        var item = itemFrame.getItemStack();
        if (item == null || item == ItemAirStack.AIR_STACK) {
            return 0;
        }
        // Signal = item rotation + 1 (range 1-8)
        return itemFrame.getItemRotation() + 1;
    }
}
