package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;

import java.util.Set;

/**
 * {@code BlockMelonBlockBaseComponentImpl} implements ordinary melon-block drops using the
 * shared Fortune helper. Melon breaking yields slices rather than the block item; the helper
 * draws from a base range and raises its upper bound with Fortune while respecting the
 * configured cap.
 *
 * @author daoge_cmd
 */
public class BlockMelonBlockBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the melon block behavior component for the supplied block type.
     */
    public BlockMelonBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns melon slices with a Fortune-adjusted random count. The count starts between
     * three and seven, and Fortune increases the upper bound up to nine; a {@code null} tool
     * contributes no Fortune level.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.MELON_SLICE.createItemStack(FortuneDropHelper.discrete(usedItem, 3, 7, 9)));
    }
}
