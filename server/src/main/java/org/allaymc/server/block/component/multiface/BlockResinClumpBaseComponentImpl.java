package org.allaymc.server.block.component.multiface;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

public class BlockResinClumpBaseComponentImpl extends BlockMultiFaceBaseComponentImpl {
    public BlockResinClumpBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var count = Integer.bitCount(block.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS));
        return Set.of(blockType.getItemType().createItemStack(count));
    }
}
