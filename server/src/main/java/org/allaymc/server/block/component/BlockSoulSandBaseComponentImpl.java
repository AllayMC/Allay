package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockSoulSandBaseComponentImpl} refreshes upward bubble columns supported by
 * soul sand. Placement and changes to the block above invoke the shared column
 * update, while the bubble component derives upward drag from the soul sand support
 * and handles retained water layers.
 *
 * @author Miroshka000
 */
public class BlockSoulSandBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the soul sand block behavior for the supplied block type.
     */
    public BlockSoulSandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards placement and refreshes the column starting above the incoming soul
     * sand block. The new block snapshot retains the old snapshot's world position
     * and layer for that update.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        BlockBubbleColumnBaseComponentImpl.updateAbove(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()));
    }

    /**
     * Forwards the neighbor event and refreshes the column for upper-neighbor
     * changes. Other faces leave bubble maintenance unchanged, while companion
     * component handlers still receive the inherited event dispatch.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.UP) {
            BlockBubbleColumnBaseComponentImpl.updateAbove(block);
        }
    }
}
