package org.allaymc.server.block.component.workstation;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.Attachment;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.container.impl.GrindstoneContainerImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.ATTACHMENT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * {@code BlockGrindstoneBaseComponentImpl} opens the grindstone menu after shared
 * block interaction handlers decline the click. Actual players receive a newly
 * created menu container associated with the clicked block, while simulated
 * players receive a handled result without opening a viewer. Placement also
 * chooses standing, hanging, or side attachment and the matching horizontal
 * orientation.
 *
 * @author IWareQ
 */
public class BlockGrindstoneBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockGrindstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a grindstone with the attachment selected by the clicked face. Floor
     * and ceiling placement face opposite the player, side placement uses the
     * clicked horizontal face, and a {@code null} context writes the supplied
     * state directly.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var clickedFace = placementInfo.blockFace();
        if (clickedFace == BlockFace.UP) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.STANDING);
            blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.player().getHorizontalFace().opposite().getHorizontalIndex());
        } else if (clickedFace == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.HANGING);
            blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.player().getHorizontalFace().opposite().getHorizontalIndex());
        } else {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.SIDE);
            blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.blockFace().getHorizontalIndex());
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Runs shared interaction handlers before opening the grindstone menu for an
     * actual player. {@code interactInfo} must identify the player and clicked
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
            var grindstoneContainer = new GrindstoneContainerImpl();
            grindstoneContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            grindstoneContainer.addViewer(player.getController());
        }
        return true;
    }
}
