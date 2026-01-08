package org.allaymc.server.block;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.position.Position3i;
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
     * Calculates the maximum power level received by a block from all 6 faces.
     * This considers both direct power and strong power through solid blocks.
     *
     * @param block the block to check
     * @return the maximum power level (0-15)
     */
    public static int getReceivedPowerLevel(Block block) {
        int maxPower = 0;
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        for (BlockFace face : BlockFace.values()) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);

            // Check direct power from neighbor
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));
            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            // Check strong power through solid blocks
            if (neighborState.getBlockStateData().isSolid() && !neighborState.getBlockStateData().isTransparent()) {
                int strongPower = getStrongPowerIntoBlock(dimension, neighborPos, face);
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    /**
     * Gets the strong power being transmitted into a solid block from a specific direction.
     * This is used to check if power can pass through a solid block.
     *
     * @param dimension the dimension
     * @param solidPos  the position of the solid block
     * @param fromFace  the face we came from (to the original block)
     * @return the strong power level (0-15)
     */
    public static int getStrongPowerIntoBlock(Dimension dimension, Vector3ic solidPos, BlockFace fromFace) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.values()) {
            // Don't check the face leading back to the original block
            if (face == fromFace) continue;

            Vector3ic checkPos = face.offsetPos(solidPos);
            BlockState state = dimension.getBlockState(checkPos);

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }

        return maxPower;
    }

    /**
     * Gets the direct signal output from a neighbor block.
     * Does not consider strong power through solid blocks.
     *
     * @param block the neighbor block
     * @param face  the face to check (from the neighbor's perspective, power going out)
     * @return the signal strength (0-15)
     */
    public static int getDirectSignal(Block block, BlockFace face) {
        return block.getBehavior().getWeakPower(block, face);
    }

    /**
     * Checks if a block is a redstone power source.
     *
     * @param block the block to check
     * @return true if the block is a power source
     */
    public static boolean isPowerSource(Block block) {
        return block.getBehavior().isPowerSource();
    }
}
