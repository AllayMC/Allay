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
 * {@code BlockStairsBaseComponentImpl} selects stair facing and upper-half placement
 * while constraining liquid flow through full faces. Player placement uses the shared
 * direction encoding and click-height helper; placement without player context
 * attempts the supplied state but reports success regardless of the dimension result.
 *
 * @author daoge_cmd
 */
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the stairs block behavior for the supplied block type.
     */
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places stairs using player facing and the clicked half when context is present.
     * A {@code null} {@code placementInfo} attempts a direct state write and returns
     * {@code true} without checking acceptance.
     */
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

    /**
     * Returns whether the collision shape leaves the requested face less than full.
     * Liquid can flow through such faces, while a completely filled face closes that
     * direction to liquid movement.
     */
    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }
}
