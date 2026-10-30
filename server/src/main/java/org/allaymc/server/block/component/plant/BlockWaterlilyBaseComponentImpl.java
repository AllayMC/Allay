package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code BlockWaterlilyBaseComponentImpl} implements waterlily placement and lower-support
 * survival. Frosted ice supports a lily directly, and any water-family state found by the
 * liquid lookup also qualifies. Lower neighbor changes recheck that world-state predicate
 * before breaking the lily, while other behavior remains inherited.
 */
public class BlockWaterlilyBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the waterlily behavior component for the supplied block type.
     */
    public BlockWaterlilyBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the lily only when the lower position has valid water or frosted-ice support.
     * Both player and programmatic placement use that test before inherited property
     * processing and the final world write.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!hasValidSupport(dimension, BlockFace.DOWN.offsetPos(placeBlockPos))) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks a lily after invalid lower support
     * changes. The predicate rereads the lower position's liquid rather than relying only on
     * the changed neighbor's base-layer state.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !hasValidSupport(block.getDimension(), BlockFace.DOWN.offsetPos(block.getPosition()))) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether {@code supportPos} contains frosted ice or water-family liquid. The
     * liquid check does not require source depth and can recognize water held in a block's
     * second layer.
     */
    protected boolean hasValidSupport(Dimension dimension, Vector3ic supportPos) {
        if (dimension.getBlockState(supportPos).getBlockType() == BlockTypes.FROSTED_ICE) {
            return true;
        }

        var liquid = dimension.getLiquid(supportPos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
