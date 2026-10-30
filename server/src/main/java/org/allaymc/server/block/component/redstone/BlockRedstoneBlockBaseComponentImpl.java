package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * {@code BlockRedstoneBlockBaseComponentImpl} supplies constant weak redstone power for
 * redstone blocks. Its output is always {@code MAX_REDSTONE_POWER} on every queried
 * face, independent of surrounding inputs or mutable properties, while inherited block
 * behavior handles ordinary world operations.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneBlockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the constant redstone source component for the supplied block type.
     */
    public BlockRedstoneBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns maximum weak power for every block snapshot and queried face.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return MAX_REDSTONE_POWER;
    }
}
