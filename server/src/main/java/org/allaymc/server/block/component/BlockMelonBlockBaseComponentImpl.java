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
 * @author daoge_cmd
 */
public class BlockMelonBlockBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockMelonBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.MELON_SLICE.createItemStack(FortuneDropHelper.discrete(usedItem, 3, 7, 9)));
    }
}
