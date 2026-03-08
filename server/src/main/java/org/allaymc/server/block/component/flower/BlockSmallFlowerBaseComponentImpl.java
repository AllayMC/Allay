package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public class BlockSmallFlowerBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockSmallFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    protected boolean canPlaceOn(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.DIRT);
    }
}
