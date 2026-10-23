package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

/**
 * {@code BlockDryGrassBaseComponentImpl} specializes short-grass behavior with an empty
 * ordinary drop set. Dirt support and neighbor-driven breaking remain inherited from short
 * grass, while this override suppresses its random wheat-seed reward for every supplied tool
 * and breaking entity.
 *
 * @author daoge_cmd
 */
public class BlockDryGrassBaseComponentImpl extends BlockShortGrassBaseComponentImpl {
    /**
     * Creates the dry grass behavior component for the supplied block type.
     */
    public BlockDryGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns no ordinary items when dry grass is broken here.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}
