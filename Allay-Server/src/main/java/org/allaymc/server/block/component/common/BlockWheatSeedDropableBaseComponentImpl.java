package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/6/18
 *
 * @author daoge_cmd
 */
public class BlockWheatSeedDropableBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockWheatSeedDropableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        var rand = ThreadLocalRandom.current();
        // 有1/8的几率掉落种子
        if (rand.nextInt(8) == 0) {
            return new ItemStack[]{ItemTypes.WHEAT_SEEDS_TYPE.createItemStack()};
        }
        return Utils.EMPTY_ITEM_STACK_ARRAY;
    }
}
