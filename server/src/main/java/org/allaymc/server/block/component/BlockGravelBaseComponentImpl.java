package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.FortuneDropHelper;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockGravelBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGravelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 10, 3)) {
            return Set.of(ItemTypes.FLINT.createItemStack());
        }

        return super.getDrops(current, usedItem, entity);
    }
}
