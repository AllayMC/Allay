package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;
import java.util.function.Supplier;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public class BlockStandingSignBaseComponentImpl extends BlockBaseComponentImpl {
    protected final Supplier<ItemType<?>> dropItemType;

    public BlockStandingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> dropItemTypeSupplier) {
        super(blockType);
        this.dropItemType = dropItemTypeSupplier;
    }

    public BlockStandingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItemId) {
        this(blockType, dropItemId::getItemType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemType.get().createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return dropItemType.get().createItemStack(1);
    }
}
