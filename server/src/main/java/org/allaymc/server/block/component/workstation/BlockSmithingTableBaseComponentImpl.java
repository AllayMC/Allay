package org.allaymc.server.block.component.workstation;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.container.impl.SmithingTableContainerImpl;

/**
 * {@code BlockSmithingTableBaseComponentImpl} opens the smithing table menu after
 * shared block interaction handlers decline the click. Actual players receive a
 * newly created menu container associated with the clicked block, while simulated
 * players receive a handled result without opening a viewer.
 *
 * @author IWareQ
 */
public class BlockSmithingTableBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockSmithingTableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared interaction handlers before opening the smithing table menu for
     * an actual player. {@code interactInfo} must identify the player and clicked
     * block; the menu receives that position, and simulated players still receive
     * {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isActualPlayer()) {
            var container = new SmithingTableContainerImpl();
            container.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            container.addViewer(player.getController());
        }
        return true;
    }
}
