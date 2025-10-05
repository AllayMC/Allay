package org.allaymc.server.block.component.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class BlockCopperDoubleSlabBaseComponentImpl extends BlockDoubleSlabBaseComponentImpl {
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId doubleSlabId) {
        super(blockType, doubleSlabId);
    }

    @Override
    public boolean canRandomUpdate() {
        return oxidationComponent.canOxidate();
    }
}
