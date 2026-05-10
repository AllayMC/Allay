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

public class BlockWaterlilyBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockWaterlilyBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!hasValidSupport(dimension, BlockFace.DOWN.offsetPos(placeBlockPos))) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !hasValidSupport(block.getDimension(), BlockFace.DOWN.offsetPos(block.getPosition()))) {
            block.breakBlock();
        }
    }

    protected boolean hasValidSupport(Dimension dimension, Vector3ic supportPos) {
        if (dimension.getBlockState(supportPos).getBlockType() == BlockTypes.FROSTED_ICE) {
            return true;
        }

        var liquid = dimension.getLiquid(supportPos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
