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
public class BlockRodBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockRodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        // We shouldn't use PlaceBlockHelper.processFacingDirectionProperty here
        // because the facing direction of a rod block is dependent on the block face the player clicked

        var face = placementInfo.blockFace();
        if (face.isHorizontal()) {
            face = face.opposite();
        }

        var clickedBlockState = placementInfo.getClickedBlockState();
        if (clickedBlockState.getBlockType() == getBlockType() && clickedBlockState.getPropertyValue(FACING_DIRECTION) == face.ordinal()) {
            face = face.opposite();
        }

        blockState = blockState.setProperty(FACING_DIRECTION, face.ordinal());
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
