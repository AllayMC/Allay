package org.allaymc.server.block.component.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;

/**
 * {@code BlockIronTrapdoorBaseComponentImpl} disables manual item interaction for
 * iron trapdoors while retaining inherited placement and redstone behavior. The
 * interaction hook leaves the trapdoor unchanged, and power-source updates continue
 * to synchronize opening through the ordinary trapdoor base implementation.
 *
 * @author harry-xi
 */
public class BlockIronTrapdoorBaseComponentImpl extends BlockTrapdoorBaseComponentImpl {
    /**
     * Creates iron trapdoor behavior for the supplied block type.
     */
    public BlockIronTrapdoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns {@code false} without toggling the iron trapdoor or forwarding
     * component interaction. This override suppresses the inherited manual opening
     * path while preserving neighbor-driven redstone changes through the base class.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        return false;
    }
}
