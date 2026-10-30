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
 * {@code BlockLanternBaseComponentImpl} selects standing or hanging lantern placement
 * from neighboring support surfaces. Player placement requires a center-full surface
 * above or below the target, with underside clicks preferring upper support;
 * placement without player context delegates to the inherited property processing.
 *
 * @author daoge_cmd
 */
public class BlockLanternBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the lantern block behavior for the supplied block type.
     */
    public BlockLanternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a lantern when either upper or lower support is valid. A {@code null}
     * {@code placementInfo} uses ordinary placement; otherwise the selected support
     * may set {@code HANGING} without clearing an existing hanging value.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        boolean downFaceClicked = placementInfo.blockFace() == BlockFace.DOWN;
        boolean isUpperValid = isUpperBlockValid(dimension, placeBlockPos);
        boolean isLowerValid = isLowerBlockValid(dimension, placeBlockPos);
        if (!isUpperValid && !isLowerValid) {
            return false;
        }
        // Underside clicks prefer the upper support; other clicks switch to hanging
        // only when lower support is unavailable and upper support remains valid.
        if ((downFaceClicked && isUpperValid) || (!downFaceClicked && !isLowerValid)) {
            blockState = blockState.setPropertyValue(BlockPropertyTypes.HANGING, true);
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Returns whether the lower block has a full upper-center support surface.
     */
    protected boolean isLowerBlockValid(Dimension dimension, Vector3ic placeBlockPos) {
        var lowerBlock = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        return lowerBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.UP);
    }

    /**
     * Returns whether the upper block has a full lower-center support surface.
     */
    protected boolean isUpperBlockValid(Dimension dimension, Vector3ic placeBlockPos) {
        var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        return upperBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.DOWN);
    }
}
