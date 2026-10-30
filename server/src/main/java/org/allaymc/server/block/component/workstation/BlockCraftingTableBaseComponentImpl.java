package org.allaymc.server.block.component.workstation;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * {@code BlockCraftingTableBaseComponentImpl} opens the crafting table menu after
 * shared block interaction handlers decline the click. Actual players receive
 * their retained player container associated with the clicked block, while
 * simulated players receive a handled result without opening a viewer.
 *
 * @author daoge_cmd
 */
public class BlockCraftingTableBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockCraftingTableBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared interaction handlers before opening the crafting table menu for
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
            var craftingTableContainer = player.getContainer(ContainerTypes.CRAFTING_TABLE);
            craftingTableContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            craftingTableContainer.addViewer(player.getController());
        }
        return true;
    }
}
