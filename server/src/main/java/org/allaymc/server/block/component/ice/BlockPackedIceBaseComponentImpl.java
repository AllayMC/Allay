package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockPackedIceBaseComponentImpl extends BlockIceBaseComponentImpl {
    public BlockPackedIceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean canRandomUpdate() {
        return false;
    }
}
