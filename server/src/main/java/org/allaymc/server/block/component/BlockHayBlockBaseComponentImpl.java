package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public class BlockHayBlockBaseComponentImpl extends BlockBaseComponentImpl{
    public BlockHayBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public float getFallDamageReductionFactor() {
        return 0.8f;
    }
}
