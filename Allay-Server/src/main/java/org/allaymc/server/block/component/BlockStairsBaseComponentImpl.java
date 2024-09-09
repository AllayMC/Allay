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
 * Allay Project 2023/9/29
 *
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
        blockState = blockState.setProperty(BlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
        if ((placementInfo.clickPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
            blockState = blockState.setProperty(BlockPropertyTypes.UPSIDE_DOWN_BIT, true);
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
