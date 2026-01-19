package org.allaymc.server.block.component.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * Component implementation for sticky piston blocks.
 *
 * @author daoge_cmd
 */
public class BlockStickyPistonBaseComponentImpl extends BlockPistonBaseComponentImpl {

    public BlockStickyPistonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected boolean isSticky() {
        return true;
    }
}
