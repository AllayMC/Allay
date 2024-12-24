package org.allaymc.server.block.component.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockStairsBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockCopperStairsBaseComponentImpl extends BlockStairsBaseComponentImpl {
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canRandomUpdate() {
        return oxidationComponent.canOxidate();
    }
}
