package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public abstract class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl implements BlockLiquidBaseComponent {

    public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    @Override
    public boolean canCollideWithEntity() {
        return true;
    }
}
