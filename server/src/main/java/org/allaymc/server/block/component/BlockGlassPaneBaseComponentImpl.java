package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Collections;
import java.util.Set;

/**
 * Glass pane behavior: connects like bars and, like glass, drops nothing when broken.
 */
public class BlockGlassPaneBaseComponentImpl extends BlockThinBaseComponentImpl {
    public BlockGlassPaneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Collections.emptySet();
    }
}
