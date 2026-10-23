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
 * {@code BlockGravelBaseComponentImpl} selects flint as a Fortune-dependent
 * alternative to the ordinary gravel item drop. A failed bonus draw delegates to the
 * shared block drop selection, while the inherited silk touch branch bypasses this
 * random alternative and retains the block item.
 *
 * @author IWareQ
 */
public class BlockGravelBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the gravel block behavior for the supplied block type.
     */
    public BlockGravelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns flint when the Fortune-adjusted bonus draw succeeds, otherwise the
     * inherited block drop. A {@code null} tool uses the base chance, and level three
     * reaches a certain flint draw.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 10, 3)) {
            return Set.of(ItemTypes.FLINT.createItemStack());
        }

        // An unsuccessful flint draw must still produce the inherited gravel item
        // selection rather than suppressing the ordinary block drop.
        return super.getDrops(block, usedItem, entity);
    }
}
