package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockHardIceBaseComponentImpl extends BlockIceBaseComponentImpl {
    public BlockHardIceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    @Override
    public boolean canRandomUpdate() {
        return false;
    }
}
