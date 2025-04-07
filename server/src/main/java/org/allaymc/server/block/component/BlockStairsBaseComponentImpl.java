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

        var blockFace = placementInfo.blockFace();
        var stairFace = placementInfo.player().getHorizontalFace();
        blockState = blockState.setPropertyValue(BlockPropertyTypes.WEIRDO_DIRECTION, toStairDirectionValue(stairFace));
        if ((placementInfo.clickedPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(BlockPropertyTypes.UPSIDE_DOWN_BIT, true);
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }

    /**
     * Get the stair direction value which represents this block face.
     *
     * @return the stair direction value.
     */
    protected static int toStairDirectionValue(BlockFace blockFace) {
        return switch (blockFace) {
            case EAST -> 0;
            case WEST -> 1;
            case SOUTH -> 2;
            case NORTH -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + blockFace);
        };
    }
}
