package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code BlockSporeBlossomBaseComponentImpl} implements ceiling support for spore blossoms
 * during placement and neighboring changes. The state directly above must be solid before
 * placement proceeds, and an upper-face neighbor change breaks the blossom if that condition
 * stops holding. Other block behavior remains inherited.
 */
public class BlockSporeBlossomBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the spore blossom behavior component for the supplied block type.
     */
    public BlockSporeBlossomBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a blossom only when the block directly above is solid. Both player and
     * programmatic calls use that ceiling test, then delegate property processing and the
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
     * Dispatches the inherited neighbor hook and breaks a blossom after invalid ceiling
     * changes. Only an upper-face change triggers this local solidity check; other faces do
     * not remove the blossom through this rule.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.UP && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the block directly above the position is solid.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        return dimension
                .getBlockState(BlockFace.UP.offsetPos(pos))
                .getBlockStateData()
                .isSolid();
    }
}
