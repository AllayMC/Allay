package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.component.annotation.Dependency;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * @author IWareQ
 */
public class BlockCopperDoorBaseComponentImpl extends BlockDoorBaseComponentImpl {
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(Block block) {
        if (!block.getPropertyValue(UPPER_BLOCK_BIT)) {
            super.onRandomUpdate(block);
        }
    }

    @Override
    public boolean canRandomUpdate() {
        // FIXME
        // return oxidationComponent.canOxidate();
        return false;
    }
}
