package org.allaymc.server.block.component.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockStickyPistonBaseComponentImpl} selects sticky behavior for the shared
 * piston implementation. Its override enables pull planning during retraction and
 * chooses sticky collision-arm blocks, while the inherited component retains power
 * checks, cancellation handling, movement execution, and animation coordination.
 *
 * @author daoge_cmd
 */
public class BlockStickyPistonBaseComponentImpl extends BlockPistonBaseComponentImpl {

    /**
     * Creates a sticky piston component for the supplied registered block type.
     */
    public BlockStickyPistonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns {@code true} so inherited piston operations enable sticky pulling
     * behavior.
     */
    @Override
    protected boolean isSticky() {
        return true;
    }
}
