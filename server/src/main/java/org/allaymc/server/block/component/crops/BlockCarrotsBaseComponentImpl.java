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
 * {@code BlockCarrotsBaseComponentImpl} implements carrot harvest drops while inheriting
 * farmland support, crop growth, and bone-meal behavior. Immature crops return one carrot,
 * while mature crops return a Fortune-adjusted carrot stack with one guaranteed item before
 * the helper counts successful binomial harvest trials.
 *
 * @author daoge_cmd
 */
public class BlockCarrotsBaseComponentImpl extends BlockCropsBaseComponentImpl {
    /**
     * Creates the carrots behavior component for the supplied block type.
     */
    public BlockCarrotsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one carrot for an immature crop and a Fortune-adjusted carrot stack for a mature
     * crop. The shared binomial helper guarantees one carrot before counting successful
     * harvest trials.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth < GROWTH.getMax()) {
            return Set.of(ItemTypes.CARROT.createItemStack());
        }

        return Set.of(ItemTypes.CARROT.createItemStack(FortuneDropHelper.binomial(usedItem, 1)));
    }
}
