package org.allaymc.api.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.CompassRoseDirection;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import static java.lang.Math.abs;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class BlockPlaceHelper {
    public static final BiMap<BlockFace, Integer> DIRECTION_4_MAPPER = HashBiMap.create(4);

    static {
        DIRECTION_4_MAPPER.put(BlockFace.NORTH, 0);
        DIRECTION_4_MAPPER.put(BlockFace.EAST, 1);
        DIRECTION_4_MAPPER.put(BlockFace.SOUTH, 2);
        DIRECTION_4_MAPPER.put(BlockFace.WEST, 3);
    }

    public static BlockState processFacingDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        var player = placementInfo.player();
        if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
            var y = player.getLocation().y() + player.getEyeHeight();
            if (y - placeBlockPos.y() > 2) {
                blockState = blockState.setPropertyValue(FACING_DIRECTION, BlockFace.UP.ordinal());
            } else if (placeBlockPos.y() - y > 0) {
                blockState = blockState.setPropertyValue(FACING_DIRECTION, BlockFace.DOWN.ordinal());
            } else {
                blockState = blockState.setPropertyValue(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
            }
        } else {
            blockState = blockState.setPropertyValue(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
        }

        return blockState;
    }

    public static BlockState processPillarAxisProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        return blockState.setPropertyValue(PILLAR_AXIS, switch (placementInfo.blockFace().getAxis()) {
            case X -> PillarAxis.X;
            case Y -> PillarAxis.Y;
            case Z -> PillarAxis.Z;
        });
    }

    public static BlockState processGroundSignDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        return blockState.setPropertyValue(BlockPropertyTypes.GROUND_SIGN_DIRECTION.createValue(
                CompassRoseDirection.getClosestFromYaw(placementInfo.player().getLocation().yaw()).getIndex()
        ));
    }

    public static BlockState processMinecraftCardinalDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        return blockState.setPropertyValue(
                MINECRAFT_CARDINAL_DIRECTION,
                placementInfo.player().getHorizontalFace().opposite().toMinecraftCardinalDirection()
        );
    }

    public static BlockState processDirection4Property(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        var playerFace = placementInfo.player().getHorizontalFace();
        return blockState.setPropertyValue(DIRECTION_4, DIRECTION_4_MAPPER.get(playerFace.opposite()));
    }

    public static BlockState processUpsideDownBitProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        var blockFace = placementInfo.blockFace();
        return blockState.setPropertyValue(
                UPSIDE_DOWN_BIT,
                (placementInfo.clickedPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN
        );
    }
}
