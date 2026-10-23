package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code BlockHeadBaseComponentImpl} orients placed heads and updates redstone-driven
 * animation for dragon and piglin heads. Other head types retain placement
 * orientation without animation changes, while the head block entity stores whether
 * animation is playing as surrounding power changes.
 *
 * @author daoge_cmd
 */
public class BlockHeadBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the head block behavior for the supplied block type.
     */
    public BlockHeadBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the head facing the clicked block face when context is available. A
     * {@code null} {@code placementInfo} delegates to ordinary property processing
     * with the supplied state and no player context.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        // Head facing accepts all six clicked faces through BlockFace ordinals, so
        // horizontal-only indices cannot represent the same floor, ceiling, and side
        // orientations.
        blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal());

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Forwards placement and enables powered dragon or piglin head animation. Other
     * head types return without animation work; this check uses the old block
     * snapshot's power query at the placement position.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        var blockType = newBlockState.getBlockType();
        if (blockType != BlockTypes.DRAGON_HEAD && blockType != BlockTypes.PIGLIN_HEAD) {
            return;
        }

        if (oldBlock.isPowered()) {
            oldBlock.<BlockEntityHead>getBlockEntity().setPlayingAnimation(true);
        }
    }

    /**
     * Forwards the update and synchronizes dragon or piglin head animation with
     * current power. Other head types remain unchanged, and the entity setter runs
     * only when its animation flag differs.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
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
