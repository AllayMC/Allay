package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockGildedBlackstoneBaseComponentImpl} selects gold nuggets as a
 * Fortune-dependent ordinary break result. A successful bonus draw produces two
 * through five nuggets, while a failed draw produces no ordinary items; inherited
 * silk touch handling still selects the block item separately.
 *
 * @author IWareQ
 */
public class BlockGildedBlackstoneBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the gilded blackstone block behavior for the supplied block type.
     */
    public BlockGildedBlackstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns two through five gold nuggets when the Fortune bonus draw succeeds. A
     * failed draw returns an empty set, and a {@code null} tool uses the helper's
     * base chance.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // The divisor reaches certainty at Fortune level three.
        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 10, 3)) {
            return Set.of(ItemTypes.GOLD_NUGGET.createItemStack(ThreadLocalRandom.current().nextInt(2, 6)));
        }

        return Collections.emptySet();
    }
}
