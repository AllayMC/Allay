package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code BlockBarrelBaseComponentImpl} exposes the barrel inventory's fullness as
 * a comparator signal. The injected holder resolves the block entity at the
 * queried position, and its container owns the signal calculation; a missing
 * entity produces zero.
 *
 * @author daoge_cmd
 */
public class BlockBarrelBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves barrel inventory state.
     * Component initialization injects this dependency before the composed block
     * behavior handles its world callbacks.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityBarrel> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockBarrelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns whether this container supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the barrel inventory's comparator fullness signal. A holder that
     * supplies {@code null} produces zero; otherwise the inventory performs the
     * fullness calculation for its current contents.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var barrel = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (barrel == null) {
            return 0;
        }
        return barrel.getContainer().calculateComparatorSignal();
    }
}
