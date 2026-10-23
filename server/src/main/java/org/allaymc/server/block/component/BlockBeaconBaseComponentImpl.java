package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;

/**
 * {@code BlockBeaconBaseComponentImpl} opens the beacon menu after shared block
 * interaction handlers have had an opportunity to handle the click. Actual players
 * view their beacon container at the clicked block position, while simulated
 * players still receive a successful interaction result.
 *
 * @author daoge_cmd
 */
public class BlockBeaconBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the beacon block component for the supplied registered block type.
     */
    public BlockBeaconBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Handles shared component interactions before opening the beacon menu for an
     * actual player. {@code interactInfo} must identify the player and clicked
     * block; a simulated player still receives {@code true} after the shared
     * handler declines.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isActualPlayer()) {
            var beaconContainer = player.getContainer(ContainerTypes.BEACON);
            beaconContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            beaconContainer.addViewer(player.getController());
        }
        return true;
    }
}
