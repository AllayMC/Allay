package org.allaymc.api.block.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.DimensionInfo;

/**
 * BlockLiquidBaseComponent is the base component for liquid blocks.
 *
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
     * Get the block state of the flowing down block.
     *
     * @return the block state of the flowing down block.
     */
    default BlockState getFlowingDownBlockState() {
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(0b1111));
    }

    /**
     * Get the liquid level of the block.
     * <p>
     * Flowing down blocks and source blocks have a liquid level of 8.
     * Other blocks have a liquid level between 1 and 7.
     *
     * @param blockState the block state to get the liquid level from.
     *
     * @return the liquid level of the block.
     */
    default int getLevel(BlockState blockState) {
        if (isFlowingDown(blockState) || isSource(blockState)) {
            return 8;
        }
        return blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b0111;
    }

    /**
     * Check if the block state represents a liquid source.
     *
     * @param blockState the block state to check.
     *
     * @return {@code true} if the block state represents a liquid source, {@code false} otherwise.
     */
    default boolean isSource(BlockState blockState) {
        return blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) == 0;
    }

    /**
     * Get the block state of the source block.
     *
     * @return the block state of the source block.
     */
    default BlockState getSourceBlockState() {
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(0));
    }

    /**
     * Get the flow decay of the liquid.
     * <p>
     * Flow decay represents how many liquid levels are lost per block flowed horizontally.
     * Affects how far the liquid can flow.
     *
     * @return the flow decay of the liquid.
     */
    int getFlowDecay(DimensionInfo dimensionInfo);

    /**
     * Get the flow speed of the liquid in ticks.
     * <p>
     * Flow speed represents how fast the liquid spreads.
     *
     * @return the flow speed of the liquid in ticks.
     */
    int getFlowSpeed(DimensionInfo dimensionInfo);

    /**
     * Check whether the block can become a source block if there are more than
     * two source block nearby horizontally.
     *
     * @return {@code true} if the block can become a source block, {@code false} otherwise.
     */
    boolean canFormSource();
}
