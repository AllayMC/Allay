package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockLanternBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockLanternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        boolean downFaceClicked = placementInfo.blockFace() == BlockFace.DOWN;
        boolean isUpperValid = isUpperBlockValid(dimension, placeBlockPos);
        boolean isLowerValid = isLowerBlockValid(dimension, placeBlockPos);

        // Return false if neither upper nor lower block is valid
        if (!isUpperValid && !isLowerValid) {
            return false;
        }

        // Set hanging property if upper block is valid when placing from below
        // or if lower block is invalid when placing from other faces
        if ((downFaceClicked && isUpperValid) || (!downFaceClicked && !isLowerValid)) {
            blockState = blockState.setPropertyValue(BlockPropertyTypes.HANGING, true);
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    protected boolean isLowerBlockValid(Dimension dimension, Vector3ic placeBlockPos) {
        var lowerBlock = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        return lowerBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.UP);
    }

    protected boolean isUpperBlockValid(Dimension dimension, Vector3ic placeBlockPos) {
        var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        return upperBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.DOWN);
    }
}
