package org.allaymc.api.block.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public interface BlockLiquidBaseComponent extends BlockBaseComponent {

    /**
     * Check if the liquid is flowing down.
     *
     * @param blockState the block state to check.
     *
     * @return {@code true} if the liquid is flowing down, {@code false} otherwise.
     */
    default boolean isFlowingDown(BlockState blockState) {
        // The first bit of the liquid depth property is set to 1 if the liquid is flowing down.
        return (blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b1000) == 0b1000;
    }

    /**
     * Get the liquid level of the block.
     *
     * @param blockState the block state to get the liquid level from.
     *
     * @return the liquid level of the block.
     */
    default int getLiquidLevel(BlockState blockState) {
        // The other three bits of the liquid depth property are used to determine the liquid level.
        return blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b0111;
    }
}
