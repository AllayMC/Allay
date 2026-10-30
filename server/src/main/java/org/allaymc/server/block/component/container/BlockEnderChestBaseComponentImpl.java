package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

/**
 * {@code BlockEnderChestBaseComponentImpl} opens each player's personal Ender
 * chest inventory from an unobstructed chest block. Shared interaction handlers
 * run first, and a solid block above prevents opening. Normal break drops contain
 * eight obsidian rather than the player's personal stored items.
 *
 * @author IWareQ
 */
public class BlockEnderChestBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockEnderChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared handlers and opens personal storage when the block above is
     * nonsolid. {@code interactInfo} must identify the clicked block and player;
     * actual players receive the container and its contents, while simulated
     * players still return {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(interactInfo.clickedBlockPos()));
        if (upperBlock.getBlockStateData().isSolid()) {
            return false;
        }

        if (player.isActualPlayer()) {
            var enderChestContainer = player.getContainer(ContainerTypes.ENDER_CHEST);
            enderChestContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            enderChestContainer.addViewer(player.getController());
            enderChestContainer.sendContents(player.getController());
        }
        return true;
    }

    /**
     * Returns one stack of eight obsidian for ordinary break drops.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.OBSIDIAN.createItemStack(8));
    }
}
