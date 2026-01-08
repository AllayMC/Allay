package org.allaymc.server.block;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Helper class for redstone power calculations.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class RedstoneHelper {

    /**
     * Checks if a position is receiving any redstone power.
     * This considers both direct weak power and strong power through solid blocks.
     *
     * @param pos the position to check
     * @return true if the position is receiving power
     */
    public static boolean isPoweredAt(Position3ic pos) {
        return getPowerAt(pos) > 0;
    }

    /**
     * Calculates the maximum power level received at a position from all 6 faces.
     * This considers both direct weak power and strong power through solid blocks.
     *
     * @param pos the position to check
     * @return the maximum power level (0-15)
     */
    public static int getPowerAt(Position3ic pos) {
        int maxPower = 0;
        Dimension dimension = pos.dimension();

        for (BlockFace face : BlockFace.values()) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            // Check direct weak power from neighbor
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));
            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            // Check strong power through opaque solid blocks
            if (neighborState.getBlockStateData().isOpaqueSolid()) {
                int strongPower = getStrongPowerAt(new Position3i(neighborPos, dimension), face);
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    /**
     * Gets the maximum strong power being received at a position from all 6 faces.
     *
     * @param pos the position to check
     * @return the strong power level (0-15)
     */
    public static int getStrongPowerAt(Position3ic pos) {
        return getStrongPowerAt(pos, new BlockFace[0]);
    }

    /**
     * Gets the maximum strong power being received at a position.
     *
     * @param pos          the position to check
     * @param excludeFaces the faces to exclude from the check
     * @return the strong power level (0-15)
     */
    public static int getStrongPowerAt(Position3ic pos, BlockFace... excludeFaces) {
        int maxPower = 0;
        Dimension dimension = pos.dimension();

        for (BlockFace face : BlockFace.values()) {
            if (excludeFaces.length > 0 && containsFace(excludeFaces, face)) continue;

            Vector3ic checkPos = face.offsetPos(pos);
            BlockState state = dimension.getBlockState(checkPos);

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }

        return maxPower;
    }

    /**
     * Gets the maximum weak power being received at a position from all 6 faces.
     *
     * @param pos the position to check
     * @return the weak power level (0-15)
     */
    public static int getWeakPowerAt(Position3ic pos) {
        return getWeakPowerAt(pos, new BlockFace[0]);
    }

    /**
     * Gets the maximum weak power being received at a position.
     *
     * @param pos          the position to check
     * @param excludeFaces the faces to exclude from the check
     * @return the weak power level (0-15)
     */
    public static int getWeakPowerAt(Position3ic pos, BlockFace... excludeFaces) {
        int maxPower = 0;
        Dimension dimension = pos.dimension();

        for (BlockFace face : BlockFace.values()) {
            if (excludeFaces.length > 0 && containsFace(excludeFaces, face)) continue;

            Vector3ic checkPos = face.offsetPos(pos);
            BlockState state = dimension.getBlockState(checkPos);

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));
            int weakPower = state.getBehavior().getWeakPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, weakPower);
        }

        return maxPower;
    }

    private static boolean containsFace(BlockFace[] faces, BlockFace target) {
        for (BlockFace face : faces) {
            if (face == target) return true;
        }
        return false;
    }
}
