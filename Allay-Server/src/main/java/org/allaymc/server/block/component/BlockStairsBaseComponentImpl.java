package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/29
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "weirdo_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upside_down_bit")
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var blockFace = placementInfo.blockFace();
        var stairFace = placementInfo.player().getHorizontalFace();
        blockState = blockState.setProperty(VanillaBlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
        if ((placementInfo.clickPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, true);
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
