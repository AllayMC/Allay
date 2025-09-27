package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;

/**
 * @author IWareQ
 */
public class BlockBrewingStandBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBrewingStandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
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

        var brewingStandContainer = player.getContainer(ContainerTypes.BREWING_STAND);
        brewingStandContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
        brewingStandContainer.addViewer(player);
        return true;
    }
}
