package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class BlockBarrelBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityBarrel> blockEntityHolderComponent;

    public BlockBarrelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var barrel = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (barrel == null) {
            return 0;
        }
        return barrel.getContainer().calculateComparatorSignal();
    }
}
