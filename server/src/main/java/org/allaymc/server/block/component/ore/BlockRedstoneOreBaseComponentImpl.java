package org.allaymc.server.block.component.ore;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.FortuneDropHelper;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockRedstoneOreBaseComponentImpl extends BlockOreBaseComponentImpl {
    public BlockRedstoneOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, ItemId.REDSTONE, 0, 0, 1, 5);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REDSTONE.createItemStack(FortuneDropHelper.discrete(usedItem, 4, 5, 8)));
    }
}
