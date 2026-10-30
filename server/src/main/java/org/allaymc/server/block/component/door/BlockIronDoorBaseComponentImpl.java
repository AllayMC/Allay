package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;

/**
 * {@code BlockIronDoorBaseComponentImpl} disables manual item interaction for iron
 * doors while retaining paired placement and redstone behavior. Its interaction
 * result leaves the door unchanged, allowing inherited neighbor-driven power checks
 * to control opening rather than player toggles through this hook.
 *
 * @author IWareQ
 */
public class BlockIronDoorBaseComponentImpl extends BlockDoorBaseComponentImpl {
    /**
     * Creates iron door base behavior for the supplied block type.
     */
    public BlockIronDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns {@code false} without opening the iron door or forwarding component
     * interaction. This override disables the manual toggle path inherited by
     * ordinary doors while preserving their redstone update behavior.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        return false;
    }
}
