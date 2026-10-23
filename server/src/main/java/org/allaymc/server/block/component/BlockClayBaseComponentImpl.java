package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;

/**
 * {@code BlockClayBaseComponentImpl} selects clay balls for ordinary clay block
 * breaks. The inherited base behavior controls tool eligibility and silk touch,
 * allowing ordinary breaks to produce four clay balls while silk touch follows the
 * block item conversion path.
 *
 * @author IWareQ
 */
public class BlockClayBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the clay block behavior for the supplied block type.
     */
    public BlockClayBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns four clay balls as the ordinary clay block drop.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.CLAY_BALL.createItemStack(4));
    }
}
