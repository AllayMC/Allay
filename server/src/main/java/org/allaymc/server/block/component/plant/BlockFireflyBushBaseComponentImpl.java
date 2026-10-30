package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code BlockFireflyBushBaseComponentImpl} implements firefly-bush ground survival during
 * placement and neighbor updates. The lower block must carry the dirt tag, including
 * programmatic placement without interaction information. Other behavior delegates to the
 * ordinary block component after this local ground requirement passes.
 */
public class BlockFireflyBushBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the firefly bush behavior component for the supplied block type.
     */
    public BlockFireflyBushBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a firefly bush after validating its lower dirt-tagged support. Both player and
     * programmatic placement use that test, then delegate property processing and the world
     * write to the base component.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks the bush after invalid lower support
     * changes. Other neighbor faces do not trigger the local dirt-tag survival check in this
     * callback.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the block directly below carries the dirt tag.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT);
    }
}
