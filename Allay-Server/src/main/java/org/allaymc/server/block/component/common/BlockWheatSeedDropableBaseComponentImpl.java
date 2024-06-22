package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;

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
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        var rand = ThreadLocalRandom.current();
        // 有1/8的几率掉落种子
        if (rand.nextInt(8) == 0) {
            return new ItemStack[]{ItemTypes.WHEAT_SEEDS_TYPE.createItemStack()};
        }
        return Utils.EMPTY_ITEM_STACK_ARRAY;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.UP && face != BlockFace.DOWN) return true;
        var blockUnder = current.pos().dimension().getBlockState(BlockFace.DOWN.offsetPos(current.pos()));
        return isPlaceableBlock(blockUnder.getBlockType());
    }

    protected boolean isPlaceableBlock(BlockType<?> block) {
        return block == GRASS_BLOCK_TYPE ||
               block == MYCELIUM_TYPE ||
               block == PODZOL_TYPE ||
               block == DIRT_TYPE ||
               block == DIRT_WITH_ROOTS_TYPE ||
               block == FARMLAND_TYPE ||
               block == MUD_TYPE ||
               block == MUDDY_MANGROVE_ROOTS_TYPE ||
               block == MOSS_BLOCK_TYPE;
    }
}
