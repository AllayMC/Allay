package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 14/07/2024
 *
 * @author IWareQ
 */
public class BlockStoneBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockStoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        return new ItemStack[]{ItemTypes.COBBLESTONE_TYPE.createItemStack()};
    }
}
