package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;

/**
 * Implementation of the redstone block, which constantly outputs maximum signal (15) to all faces.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneBlockBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockRedstoneBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return MAX_REDSTONE_POWER;
    }
}
