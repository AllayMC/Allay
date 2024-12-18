package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;

/**
 * @author IWareQ
 */
public class BlockIronDoorBaseComponentImpl extends BlockDoorBaseComponentImpl {
    public BlockIronDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        return true;
    }
}
