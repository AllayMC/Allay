package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.BlockPlaceHelper;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.Attachment;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.impl.GrindstoneContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.BlockPlaceHelper.DIRECTION_4_MAPPER;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.ATTACHMENT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * @author IWareQ
 */
public class BlockGrindstoneBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGrindstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        blockState = BlockPlaceHelper.processDirection4Property(blockState, placeBlockPos, placementInfo);

        var clickedFace = placementInfo.blockFace();
        if (clickedFace == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.HANGING);
        } else if (clickedFace == BlockFace.UP) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.STANDING);
        } else {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.SIDE);
            blockState = blockState.setPropertyValue(DIRECTION_4, DIRECTION_4_MAPPER.get(placementInfo.blockFace().opposite()));
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
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

        var grindstoneContainer = new GrindstoneContainer();
        grindstoneContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
        grindstoneContainer.addViewer(player);
        return true;
    }
}
