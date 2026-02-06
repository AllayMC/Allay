package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.dto.NeighborUpdateContext;
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
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        BlockFace frameFacing = BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
        if (frameFacing == null || face != frameFacing.opposite()) {
            return;
        }

        // Break if attached block no longer has a full surface
        if (!neighbor.getBlockStateData().collisionShape().isFull(frameFacing)) {
            block.breakBlock();
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        BlockFace clickedFace = placementInfo.blockFace();
        // Verify the attachment block has a full surface on the attached face
        if (!placementInfo.getClickedBlock().getBlockStateData().collisionShape().isFull(clickedFace)) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, clickedFace.ordinal()));
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
