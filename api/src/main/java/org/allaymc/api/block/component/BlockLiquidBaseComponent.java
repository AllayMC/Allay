package org.allaymc.api.block.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.DimensionInfo;

/**
 * BlockLiquidBaseComponent is the base component for liquid blocks.
 *
 * @author daoge_cmd
 */
public interface BlockLiquidBaseComponent extends BlockBaseComponent {
    /**
     * Check if the liquid is falling.
     *
     * @param blockState the block state to check.
     *
     * @return {@code true} if the liquid is flowing down, {@code false} otherwise.
     */
    static boolean isFalling(BlockState blockState) {
        // The first bit of the liquid depth property is set to 1 if the liquid is falling
        return (blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b1000) == 0b1000;
    }

    /**
     * Get the liquid depth of the block.
     * <p>
     * Falling blocks and source blocks have a liquid depth of 8.
     * Other blocks have a liquid depth between 1 and 7.
     *
     * @param blockState the block state to get the liquid depth from.
     *
     * @return the liquid depth of the block.
     */
    static int getDepth(BlockState blockState) {
        if (isFalling(blockState) || isSource(blockState)) {
            return 8;
        }
        return 8 - blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b0111;
    }

    /**
     * Check if the block state represents a liquid source.
     *
     * @param blockState the block state to check.
     *
     * @return {@code true} if the block state represents a liquid source, {@code false} otherwise.
     */
    static boolean isSource(BlockState blockState) {
        return blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) == 0;
    }

    /**
     * Get the block state of the liquid block with given depth and falling state.
     *
     * @param depth   the depth of the liquid.
     * @param falling {@code true} if the liquid is falling, {@code false} otherwise.
     *
     * @return the block state of the liquid block with given depth and falling state.
     */
    default BlockState getLiquidBlockState(int depth, boolean falling) {
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(falling ? 0b1000 | 8 - depth : 8 - depth));
    }

    /**
     * Get the block state of the falling block.
     *
     * @return the block state of the falling block.
     */
    default BlockState getFallingBlockState() {
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(0b1000));
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

    /**
     * Check if the given block type is the same liquid type as this block type.
     *
     * @param blockType the block type to check.
     *
     * @return {@code true} if the given block type is the same liquid type as this block type, {@code false} otherwise.
     */
    boolean isSameLiquidType(BlockType<?> blockType);
}
