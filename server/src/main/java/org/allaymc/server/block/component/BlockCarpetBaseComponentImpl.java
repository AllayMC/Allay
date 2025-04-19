package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;

/**
 * @author IWareQ
 */
public class BlockCarpetBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCarpetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face == BlockFace.DOWN && neighbor.isAir()) {
            current.breakBlock();
        }
    }
}
