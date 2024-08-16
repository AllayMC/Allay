package org.allaymc.server.block.component.facing;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static java.lang.Math.abs;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public class BlockFacingDirectionComponentImpl extends BlockBaseComponentImpl {
    public BlockFacingDirectionComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }
        var player = placementInfo.player();
        if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
            var y = player.getLocation().y() + player.getEyeHeight();
            if (y - placeBlockPos.y() > 2) {
                blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.UP.ordinal());
            } else if (placeBlockPos.y() - y > 0) {
                blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.DOWN.ordinal());
            } else {
                blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
            }
        } else {
            blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
