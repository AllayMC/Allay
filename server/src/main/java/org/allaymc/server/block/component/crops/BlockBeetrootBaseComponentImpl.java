package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * {@code BlockBeetrootBaseComponentImpl} implements beetroot harvest drops while inheriting
 * farmland support, crop growth, and bone-meal behavior. Immature crops return one seed, while
 * mature crops retain that seed and add a beetroot stack whose count comes from the Fortune
 * helper's binomial harvest calculation.
 *
 * @author daoge_cmd
 */
public class BlockBeetrootBaseComponentImpl extends BlockCropsBaseComponentImpl {
    /**
     * Creates the beetroot behavior component for the supplied block type.
     */
    public BlockBeetrootBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one seed for an immature beetroot crop. A mature crop returns one seed plus a
     * beetroot stack whose count follows the Fortune helper's binomial result with zero
     * guaranteed harvest items.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth < GROWTH.getMax()) {
            return Set.of(ItemTypes.BEETROOT_SEEDS.createItemStack());
        }

        return Set.of(ItemTypes.BEETROOT_SEEDS.createItemStack(), ItemTypes.BEETROOT.createItemStack(FortuneDropHelper.binomial(usedItem, 0)));
    }
}
