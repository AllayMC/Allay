package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Collections;
import java.util.Set;

/**
 * {@code BlockGlassBaseComponentImpl} suppresses ordinary glass item drops while
 * retaining shared block behavior. The base break handler still selects its silk
 * touch path before ordinary drops, so this empty selection affects ordinary glass
 * breaks rather than the specialized silk touch conversion.
 *
 * @author daoge_cmd
 */
public class BlockGlassBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the glass block behavior for the supplied block type.
     */
    public BlockGlassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns an empty set for the ordinary glass drop selection. The inherited silk
     * touch branch bypasses this method and asks for the block's silk touch item
     * separately.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Collections.emptySet();
    }
}
