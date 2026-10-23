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
 * {@code BlockFrameBaseComponentImpl} handles item frame attachment and comparator
 * input through an injected frame entity holder. Placement requires a full surface on
 * the clicked support face, support changes can break the frame, and displayed item
 * rotation determines its nonzero comparator signal.
 *
 * @author daoge_cmd
 */
public class BlockFrameBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Injected item frame entity holder.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityItemFrame> blockEntityHolderComponent;

    /**
     * Creates the frame block behavior for the supplied block type.
     */
    public BlockFrameBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the update and checks the frame's supporting face when that neighbor
     * changes. Invalid facing values skip support checks, while a support without a
     * full attachment surface breaks the frame.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        BlockFace frameFacing = BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
        if (frameFacing == null || face != frameFacing.opposite()) {
            return;
        }
        if (!neighbor.getBlockStateData().collisionShape().isFull(frameFacing)) {
            block.breakBlock();
        }
    }

    /**
     * Places a frame on a full clicked support face and records that facing. A
     * {@code null} {@code placementInfo} writes the supplied state without performing
     * the player attachment check.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        BlockFace clickedFace = placementInfo.blockFace();
        if (!placementInfo.getClickedBlock().getBlockStateData().collisionShape().isFull(clickedFace)) {
            return false;
        }

        // The numeric facing property uses BlockFace declaration order, so the stored
        // orientation can be decoded with fromIndex during later frame support
        // checks.
        return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, clickedFace.ordinal()));
    }

    /**
     * Returns whether item frames always support specialized comparator input.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the displayed item rotation plus one for a populated frame. A missing
     * frame entity, {@code null} item, or air item returns {@code 0} rather than a
     * rotation signal.
     */
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
        // The extra unit distinguishes a displayed item from an empty frame.
        return itemFrame.getItemRotation() + 1;
    }
}
