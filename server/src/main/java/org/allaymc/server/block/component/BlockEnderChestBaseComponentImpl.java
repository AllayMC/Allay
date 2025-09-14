package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.container.impl.BlockContainerImpl;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockEnderChestBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockEnderChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isSneaking()) {
            return false;
        }

        var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(interactInfo.clickedBlockPos()));
        if (!upperBlock.getBlockStateData().isTransparent()) {
            return false;
        }

        var enderChestContainer = player.getContainer(ContainerType.ENDER_CHEST);
        ((BlockContainerImpl) enderChestContainer).setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
        enderChestContainer.addViewer(player);
        enderChestContainer.sendContents(player);
        return true;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.OBSIDIAN.createItemStack(8));
    }
}
