package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * {@code BlockSmallFlowerBaseComponentImpl} implements the lower-support rule shared by small
 * flowers. Neighbor notifications first reach the ordinary block hook, then a lower-face
 * change breaks the flower when the supporting type lacks the dirt tag. Other placement and
 * interaction behavior remains inherited.
 *
 * @author daoge_cmd
 */
public class BlockSmallFlowerBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the small flower behavior component for the supplied block type.
     */
    public BlockSmallFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the inherited neighbor hook and removes flowers after invalid lower support
     * changes. Only the lower face invokes the local dirt-tag test; other neighboring changes
     * do not trigger this support branch.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the supporting block type carries the dirt tag.
     */
    protected boolean canPlaceOn(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.DIRT);
    }
}
