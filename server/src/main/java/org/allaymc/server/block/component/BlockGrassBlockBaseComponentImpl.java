package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    // TODO: spread logic
}
