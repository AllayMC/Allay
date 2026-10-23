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
 * {@code BlockWheatBaseComponentImpl} implements wheat harvest drops while inheriting farmland
 * support, crop growth, and bone-meal behavior. Immature crops return one seed, while mature
 * crops retain that seed and add a wheat stack whose count comes from the Fortune helper's
 * binomial harvest calculation.
 *
 * @author daoge_cmd
 */
public class BlockWheatBaseComponentImpl extends BlockCropsBaseComponentImpl {
    /**
     * Creates the wheat behavior component for the supplied block type.
     */
    public BlockWheatBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one seed for an immature wheat crop. A mature crop returns one seed plus a wheat
     * stack whose count follows the Fortune helper's binomial result with zero guaranteed
     * wheat items.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth < GROWTH.getMax()) {
            return Set.of(ItemTypes.WHEAT_SEEDS.createItemStack());
        }

        return Set.of(ItemTypes.WHEAT_SEEDS.createItemStack(), ItemTypes.WHEAT.createItemStack(FortuneDropHelper.binomial(usedItem, 0)));
    }
}
