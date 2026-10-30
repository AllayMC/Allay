package org.allaymc.server.block.component.snow;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

/**
 * {@code BlockSnowBaseComponentImpl} implements ordinary snow-block drops through a fixed
 * snowball stack. This component supplies four snowballs when the base breaking path requests
 * ordinary drops; inherited drop eligibility and silk-touch handling decide whether that
 * ordinary calculation is used.
 *
 * @author IWareQ
 */
public class BlockSnowBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the snow behavior component for the supplied block type.
     */
    public BlockSnowBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns four snowballs when ordinary snow-block drops are requested here.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.SNOWBALL.createItemStack(4));
    }
}
