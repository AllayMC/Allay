package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockHeadBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHeadBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal());

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
