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
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockDoubleSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockDoubleSlabBaseComponent {
    protected final Supplier<BlockType<?>> singleSlabType;

    public BlockDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> singleSlabTypeSupplier) {
        super(blockType);
        this.singleSlabType = singleSlabTypeSupplier;
    }

    public BlockDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId singleSlabId) {
        this(blockType, singleSlabId::getBlockType);
    }

    @Override
    public BlockType<?> getSingleSlabBlockType() {
        return singleSlabType.get();
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(getSingleSlabBlockType().getItemType().createItemStack(2));
    }
}
