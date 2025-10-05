package org.allaymc.server.block.component.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockDoubleSlabBaseComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockDoubleSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockDoubleSlabBaseComponent {
    protected BlockId singleSlabId;

    public BlockDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId singleSlabId) {
        super(blockType);
        this.singleSlabId = singleSlabId;
    }

    @Override
    public BlockType<?> getSingleSlabBlockType() {
        return singleSlabId.getBlockType();
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(getSingleSlabBlockType().getItemType().createItemStack(2));
    }
}
