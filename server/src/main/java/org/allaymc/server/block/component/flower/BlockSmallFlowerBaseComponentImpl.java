package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockSmallFlowerBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockSmallFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    protected boolean canPlaceOn(BlockType<?> blockType) {
        return blockType == GRASS_BLOCK ||
               blockType == MYCELIUM ||
               blockType == PODZOL ||
               blockType == DIRT ||
               blockType == DIRT_WITH_ROOTS ||
               blockType == FARMLAND ||
               blockType == MUD ||
               blockType == MUDDY_MANGROVE_ROOTS ||
               blockType == MOSS_BLOCK;
    }
}
