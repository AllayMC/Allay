package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public class BlockCarpetBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCarpetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        if (face == BlockFace.DOWN && neighbor.isAir()) {
            block.breakBlock();
        }
    }
}
