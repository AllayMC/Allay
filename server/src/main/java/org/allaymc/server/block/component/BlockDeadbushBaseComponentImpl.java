package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * @author IWareQ
 */
public class BlockDeadbushBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockDeadbushBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face == BlockFace.DOWN && !canBeSupportedAt(neighbor)) {
            current.breakBlock();
        }
    }

    private boolean canBeSupportedAt(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockState.getBehavior() instanceof BlockTerracottaBehavior ||
               blockType == SAND ||
               blockType == RED_SAND ||
               blockType == DIRT ||
               blockType == PODZOL ||
               blockType == MUD ||
               blockType == GRASS_BLOCK ||
               blockType == MYCELIUM ||
               blockType == MOSS_BLOCK;
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(current, usedItem, entity);
        }

        return Set.of(ItemTypes.STICK.createItemStack(ThreadLocalRandom.current().nextInt(0, 3)));
    }
}
