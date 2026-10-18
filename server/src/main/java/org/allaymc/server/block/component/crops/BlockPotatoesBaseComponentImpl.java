package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH_8;

/**
 * {@code BlockPotatoesBaseComponentImpl} implements potato harvest drops while inheriting
 * farmland support, crop growth, and bone-meal behavior. Immature crops return one potato;
 * mature crops return a Fortune-adjusted potato stack and independently add a poisonous potato
 * with two-percent probability.
 *
 * @author daoge_cmd
 */
public class BlockPotatoesBaseComponentImpl extends BlockCropsBaseComponentImpl {
    /**
     * Creates the potatoes behavior component for the supplied block type.
     */
    public BlockPotatoesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one potato for an immature crop and a Fortune-adjusted potato stack when mature.
     * A mature harvest independently adds one poisonous potato with two-percent probability;
     * the helper guarantees one ordinary potato.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH_8);
        if (growth < GROWTH_8.getMax()) {
            return Set.of(ItemTypes.POTATO.createItemStack());
        }

        var potatoDropCount = FortuneDropHelper.binomial(usedItem, 1);
        // Poisonous potatoes use a fixed bonus chance independent of Fortune harvest quantity.
        if (ThreadLocalRandom.current().nextFloat() < 0.02f) {
            return Set.of(ItemTypes.POTATO.createItemStack(potatoDropCount), ItemTypes.POISONOUS_POTATO.createItemStack());
        }

        return Set.of(ItemTypes.POTATO.createItemStack(potatoDropCount));
    }
}
