package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;

/**
 * {@code BlockStoneBaseComponentImpl} selects cobblestone for the ordinary stone
 * break path. The shared base behavior handles drop eligibility and asks for a
 * separate silk touch result, allowing ordinary mining to produce cobblestone while
 * silk touch retains the stone item.
 *
 * @author daoge_cmd
 */
public class BlockStoneBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the stone block behavior for the supplied block type.
     */
    public BlockStoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one cobblestone item as the ordinary stone block drop.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.COBBLESTONE.createItemStack(1));
    }
}
