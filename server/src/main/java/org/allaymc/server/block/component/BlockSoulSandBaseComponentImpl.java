package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
public class BlockSoulSandBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSoulSandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        BlockBubbleColumnBaseComponentImpl.updateAbove(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()));
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.UP) {
            BlockBubbleColumnBaseComponentImpl.updateAbove(block);
        }
    }
}
