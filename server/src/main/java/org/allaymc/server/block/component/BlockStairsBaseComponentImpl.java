package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.BlockPlaceHelper;
import org.joml.Vector3ic;

import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;

/**
 * @author daoge_cmd
 */
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var stairFace = placementInfo.player().getHorizontalFace();
        blockState = blockState.setPropertyValue(BlockPropertyTypes.WEIRDO_DIRECTION, EWSN_DIRECTION_4_MAPPER.get(stairFace));
        blockState = BlockPlaceHelper.processUpsideDownBitProperty(blockState, placeBlockPos, placementInfo);
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }
}
