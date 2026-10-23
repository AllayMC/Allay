package org.allaymc.server.block;

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
 * {@code BlockPlaceHelper} derives block placement properties from player
 * orientation, the clicked face, and the click position. Registry-backed property
 * processors use these helpers to select facing, pillar axis, sign rotation, cardinal
 * direction, and top-half placement. Missing player placement context preserves the
 * supplied block state rather than inventing an orientation.
 *
 * <p>
 * Facing selection considers the player's eye height only when the player is
 * horizontally close to the target; more distant placement uses the opposite
 * horizontal facing. Stairs and trapdoors also share a dedicated direction encoding
 * whose order differs from face ordinals. Keeping that mapping separate lets those
 * blocks use their protocol property values without changing the general facing
 * calculation.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class BlockPlaceHelper {

    /**
     * {@code EWSN_DIRECTION_4_MAPPER} maps east, west, south, and north to their
     * stair and trapdoor property values. The public mutable mapping is also visible
     * to every placement caller that reads it.
     */
    public static final BiMap<BlockFace, Integer> EWSN_DIRECTION_4_MAPPER = HashBiMap.create(4);

    // Initialize the shared stair and trapdoor direction encoding before placement
    // helpers read its entries.
    static {
        EWSN_DIRECTION_4_MAPPER.put(BlockFace.EAST, 0);
        EWSN_DIRECTION_4_MAPPER.put(BlockFace.WEST, 1);
        EWSN_DIRECTION_4_MAPPER.put(BlockFace.SOUTH, 2);
        EWSN_DIRECTION_4_MAPPER.put(BlockFace.NORTH, 3);
    }

    /**
     * Returns the state with facing chosen from the player's relative position and
     * orientation. A {@code null} {@code placementInfo} preserves the input state.
     *
     * <p>
     * Players within two blocks horizontally can place an upward-facing block when
     * their eyes are more than two blocks above the target, or a downward-facing
     * block when their eyes are below it. Other cases face opposite the player's
     * horizontal direction, so distance prevents unrelated vertical facing changes.
     */
    public static BlockState processFacingDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        // FACING_DIRECTION uses the six-face BlockFace ordinal encoding in every
        // branch. Keep that order distinct from horizontal-only direction indices
        // when selecting placement orientation.
        var player = placementInfo.player();
        // The two-block horizontal window limits eye-height-based vertical placement
        // to players near the target.
        if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
            var y = player.getLocation().y() + player.getEyeHeight();
            // A two-block eye-height gap selects upward facing.
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

    /**
     * Returns the state with its pillar axis matching the clicked face's axis. A
     * {@code null} {@code placementInfo} preserves the input state, and
     * {@code placeBlockPos} does not affect this calculation.
     */
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

    /**
     * Returns the state with sign rotation selected from the player's yaw. A
     * {@code null} {@code placementInfo} preserves the input state; the nearest
     * compass-rose direction supplies the rotation index.
     */
    public static BlockState processGroundSignDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        // GROUND_SIGN_DIRECTION uses the compass-rose rotation index rather than a
        // six-face or four-face direction value, preserving the finer rotation steps
        // selected from yaw.
        return blockState.setPropertyValue(BlockPropertyTypes.GROUND_SIGN_DIRECTION.createValue(
                CompassRoseDirection.getClosestFromYaw(placementInfo.player().getLocation().yaw()).getIndex()
        ));
    }

    /**
     * Returns the state facing opposite the player's horizontal cardinal direction. A
     * {@code null} {@code placementInfo} preserves the input state, and
     * {@code placeBlockPos} does not affect the horizontal orientation selected here.
     */
    public static BlockState processMinecraftCardinalDirectionProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        return blockState.setPropertyValue(
                MINECRAFT_CARDINAL_DIRECTION,
                placementInfo.player().getHorizontalFace().opposite().toMinecraftCardinalDirection()
        );
    }

    /**
     * Returns the state with top-half placement selected from the clicked face and
     * height. A {@code null} {@code placementInfo} preserves the input; underside
     * clicks always select the top half.
     */
    public static BlockState processUpsideDownBitProperty(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return blockState;
        }

        var blockFace = placementInfo.blockFace();
        return blockState.setPropertyValue(
                UPSIDE_DOWN_BIT,
                // The half-block boundary separates lower and upper side clicks.
                (placementInfo.clickedPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN
        );
    }
}
