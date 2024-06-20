package org.allaymc.server.block.component.grassblock;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/6/15
 *
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        return new ItemStack[]{ItemTypes.DIRT_TYPE.createItemStack()};
    }
}
