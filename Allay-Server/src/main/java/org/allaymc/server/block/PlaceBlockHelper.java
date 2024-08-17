package org.allaymc.server.block;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.data.CompassRoseDirection;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.joml.Vector3ic;

import static java.lang.Math.abs;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;

/**
 * Allay Project 2024/8/15
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class PlaceBlockHelper {
    public static BlockState processFacingDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) return blockState;
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
        return blockState;
    }

    public static BlockState processGroundSignDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) return blockState;
        blockState = blockState.setProperty(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION.createValue(
                CompassRoseDirection.getClosestFromYaw(placementInfo.player().getLocation().yaw()).getIndex()
        ));
        return blockState;
    }
}
