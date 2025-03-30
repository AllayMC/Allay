package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.FortuneDropHelper;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class BlockGildedBlackstoneBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGildedBlackstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 10, 3)) {
            return Set.of(ItemTypes.GOLD_NUGGET.createItemStack(ThreadLocalRandom.current().nextInt(2, 5)));
        }

        return super.getDrops(blockState, usedItem, entity);
    }
}
