package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.*;

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
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        var rand = ThreadLocalRandom.current();
        // Has a 1/8 chance to drop seeds
        if (rand.nextInt(8) == 0) {
            return Set.of(ItemTypes.WHEAT_SEEDS.createItemStack());
        }
        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.UP && face != BlockFace.DOWN) return true;
        var blockUnder = current.pos().dimension().getBlockState(BlockFace.DOWN.offsetPos(current.pos()));
        return this.canPlaceOnBlock(blockUnder.getBlockType());
    }

    @Override
    public boolean canPlaceOnBlock(BlockType<?> blockType) {
        return blockType == GRASS_BLOCK ||
               blockType == MYCELIUM ||
               blockType == PODZOL ||
               blockType == DIRT ||
               blockType == DIRT_WITH_ROOTS ||
               blockType == FARMLAND ||
               blockType == MUD ||
               blockType == MUDDY_MANGROVE_ROOTS ||
               blockType == MOSS_BLOCK;
    }
}
