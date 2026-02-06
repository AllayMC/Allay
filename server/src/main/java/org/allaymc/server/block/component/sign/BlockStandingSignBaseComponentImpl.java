package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public class BlockStandingSignBaseComponentImpl extends BlockBaseComponentImpl {
    protected ItemId dropItemId;

    public BlockStandingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItemId) {
        super(blockType);
        this.dropItemId = dropItemId;
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var face = context.face();
        var neighbor = context.neighbor();
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemId.getItemType().createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return dropItemId.getItemType().createItemStack(1);
    }
}
