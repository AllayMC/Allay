package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(blockState, usedItem, entity);
        }
        // TODO: Drop saplings
        // https://minecraft.fandom.com/zh/wiki/%E6%A0%91%E5%8F%B6#%E8%8E%B7%E5%8F%96
        return Utils.EMPTY_ITEM_STACK_SET;
    }
}
