package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * @author daoge_cmd
 */
public class BlockSoulFireBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSoulFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        var downBlockType = block.offsetPos(BlockFace.DOWN).getBlockType();
        if (downBlockType != BlockTypes.SOUL_SAND && downBlockType != BlockTypes.SOUL_SOIL) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.FIRE.copyPropertyValuesFrom(block.getBlockState()));
        }
    }
}
