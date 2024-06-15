package org.allaymc.server.block.component.glass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/6/15
 *
 * @author daoge_cmd
 */
public class BlockGlassBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGlassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockState blockState, ItemStack usedItem) {
        return Utils.EMPTY_ITEM_STACK_ARRAY;
    }
}
