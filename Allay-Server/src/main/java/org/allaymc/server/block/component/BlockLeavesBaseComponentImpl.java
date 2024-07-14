package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/7/10
 *
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        if (usedItem.getItemType() == ItemTypes.SHEARS_TYPE) {
            return new ItemStack[]{blockState.blockState().toItemStack()};
        }
        // TODO: Drop saplings
        // https://minecraft.fandom.com/zh/wiki/%E6%A0%91%E5%8F%B6#%E8%8E%B7%E5%8F%96
        return Utils.EMPTY_ITEM_STACK_ARRAY;
    }
}
