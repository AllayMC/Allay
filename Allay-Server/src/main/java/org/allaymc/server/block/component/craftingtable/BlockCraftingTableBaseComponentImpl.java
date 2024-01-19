package org.allaymc.server.block.component.craftingtable;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

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
    public boolean onInteract(EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        if (!super.onInteract(player, itemStack, dimension, blockPos, placeBlockPos, clickPos, blockFace) && player != null) {
            var craftingTableContainer = player.getContainer(FullContainerType.CRAFTING_TABLE);
            craftingTableContainer.setBlockPos(blockPos);
            craftingTableContainer.addViewer(player);
            return true;
        }
        return true;
    }
}
