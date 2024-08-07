package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class BlockCraftingTableBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCraftingTableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;

        var player = interactInfo.player();
        var craftingTableContainer = player.getContainer(FullContainerType.CRAFTING_TABLE);
        craftingTableContainer.setBlockPos(interactInfo.clickBlockPos());
        craftingTableContainer.addViewer(player);
        return true;
    }
}
