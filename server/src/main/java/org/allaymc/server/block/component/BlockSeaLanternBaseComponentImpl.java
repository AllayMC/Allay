package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
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
public class BlockSeaLanternBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSeaLanternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.PRISMARINE_CRYSTALS.createItemStack(ThreadLocalRandom.current().nextInt(2, 4)));
    }
}
