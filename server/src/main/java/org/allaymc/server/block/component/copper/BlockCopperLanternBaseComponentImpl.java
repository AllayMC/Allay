package org.allaymc.server.block.component.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockLanternBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class BlockCopperLanternBaseComponentImpl extends BlockLanternBaseComponentImpl {
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperLanternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canRandomUpdate() {
        return oxidationComponent.canOxidate();
    }
}
