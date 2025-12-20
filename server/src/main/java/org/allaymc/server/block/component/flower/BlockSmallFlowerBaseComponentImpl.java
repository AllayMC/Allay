package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockSmallFlowerBaseComponentImpl extends BlockBaseComponentImpl {

    protected final ItemId flowerItemId;

    public BlockSmallFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId flowerItemId) {
        super(blockType);
        this.flowerItemId = flowerItemId;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    protected boolean canPlaceOn(BlockType<?> blockType) {
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

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(flowerItemId.getItemType().createItemStack());
    }
}
