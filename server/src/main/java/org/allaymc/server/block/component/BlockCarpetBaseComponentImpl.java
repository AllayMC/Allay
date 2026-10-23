package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockCarpetBaseComponentImpl} handles carpet support removal while retaining
 * shared block placement and drops. A lower neighbor becoming air breaks the carpet
 * through the normal block break path, while changes to other faces only reach
 * companion component handlers.
 *
 * @author IWareQ
 */
public class BlockCarpetBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the carpet block behavior for the supplied block type.
     */
    public BlockCarpetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the neighbor event and breaks carpet when the lower neighbor becomes
     * air. The check uses only the air state, so other nonsolid supports do not
     * trigger this branch.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && neighbor.isAir()) {
            block.breakBlock();
        }
    }
}
