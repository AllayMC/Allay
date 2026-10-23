package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

/**
 * {@code BlockHardIceBaseComponentImpl} specializes ice behavior to disable random melting and
 * water-producing ordinary drops. The component retains inherited block hooks, but normal
 * random-update eligibility is {@code false} and drop calculation returns an empty set without
 * changing the broken position to water.
 *
 * @author IWareQ
 */
public class BlockHardIceBaseComponentImpl extends BlockIceBaseComponentImpl {
    /**
     * Creates the hard ice behavior component for the supplied block type.
     */
    public BlockHardIceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns no ordinary drops and performs no water replacement here.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    /**
     * Returns whether hard ice receives random melting updates, which it disables.
     */
    @Override
    public boolean canRandomUpdate() {
        return false;
    }
}
