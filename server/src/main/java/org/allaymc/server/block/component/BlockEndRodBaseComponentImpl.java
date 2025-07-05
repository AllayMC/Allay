package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * @author Dhaiven
 */
public class BlockEndRodBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockEndRodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.blockFace();
        if (face.isHorizontal()) {
            face = face.opposite();
        }

        var clickedBlockState = placementInfo.getClickedBlockState();
        if (clickedBlockState.getBlockType() == getBlockType() && clickedBlockState.getPropertyValue(FACING_DIRECTION) == face.ordinal()) {
            face = face.opposite();
        }

        blockState = blockState.setPropertyValue(FACING_DIRECTION, face.ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
