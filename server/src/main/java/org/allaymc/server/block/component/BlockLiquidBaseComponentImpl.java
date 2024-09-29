package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl {
     public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }
}
