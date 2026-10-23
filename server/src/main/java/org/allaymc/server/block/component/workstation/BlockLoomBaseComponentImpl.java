package org.allaymc.server.block.component.workstation;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * {@code BlockLoomBaseComponentImpl} opens the loom menu after shared block
 * interaction handlers decline the click. Actual players receive their retained
 * player container associated with the clicked block, while simulated players
 * receive a handled result without opening a viewer.
 *
 * @author daoge_cmd
 */
public class BlockLoomBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockLoomBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a loom facing opposite the player's horizontal direction when player
     * placement information is supplied. A {@code null} context retains the
     * state's existing direction and is passed through to the dimension write.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.player().getHorizontalFace().opposite().getHorizontalIndex());
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Runs shared interaction handlers before opening the loom menu for an actual
     * player. {@code interactInfo} must identify the player and clicked block; the
     * menu receives that position, and simulated players still receive
     * {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isActualPlayer()) {
            var loomContainer = player.getContainer(ContainerTypes.LOOM);
            loomContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            loomContainer.addViewer(player.getController());
        }
        return true;
    }
}
