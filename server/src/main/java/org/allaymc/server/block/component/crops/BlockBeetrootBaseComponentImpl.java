package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockBeetrootBaseComponentImpl extends BlockCropsBaseComponentImpl {
    public BlockBeetrootBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        var growth = blockState.blockState().getPropertyValue(BlockPropertyTypes.GROWTH);
        if (growth < 7) {
            return Set.of(ItemTypes.BEETROOT_SEEDS.createItemStack());
        }

        return Set.of(ItemTypes.BEETROOT_SEEDS.createItemStack(), ItemTypes.BEETROOT.createItemStack(ThreadLocalRandom.current().nextInt(4) + 1));
    }
}
