package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.dimension.DimensionType;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code BlockLiquidBaseComponent} defines liquid depth encoding, source recognition, and the
 * flow characteristics used by liquid block behaviors. The static helpers interpret {@code
 * LIQUID_DEPTH}; implementations supply dimension-dependent spread timing, horizontal decay,
 * liquid-family matching, and optional hardening behavior.
 *
 * @author daoge_cmd
 */
public interface BlockLiquidBaseComponent extends BlockBaseComponent {
    /**
     * Returns whether {@code blockState} encodes downward liquid flow. The state must expose
     * {@code LIQUID_DEPTH}; bit {@code 0b1000} distinguishes falling states from horizontal
     * flow and full liquid source blocks.
     */
    static boolean isFalling(BlockState blockState) {
        // Bit 3 marks falling independently of the lower decay bits, allowing
        // source recognition and effective-depth calculations to interpret the same stored property differently.
        return (blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b1000) == 0b1000;
    }

    /**
     * Returns the effective liquid depth used by flow calculations. Sources and falling states
     * both yield {@code 8}; other states yield {@code 8} minus the three low encoding bits,
     * giving depths from {@code 1} through {@code 7}.
     */
    static int getDepth(BlockState blockState) {
        // Sources and falling columns both supply full effective depth to flow
        // calculations even though their stored falling flags and decay encodings differ.
        if (isFalling(blockState) || isSource(blockState)) {
            return 8;
        }
        return 8 - (blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) & 0b0111);
    }

    /**
     * Returns whether {@code blockState} has the liquid-source encoding. The state must expose
     * {@code LIQUID_DEPTH}; only an encoded value of {@code 0} identifies a source, rather
     * than a falling state.
     */
    static boolean isSource(BlockState blockState) {
        return blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) == 0;
    }

    /**
     * Returns this liquid type's state with the requested depth and falling flag. Callers
     * supply a depth from {@code 1} through {@code 8}; {@code falling} sets the high flag
     * while the remaining bits encode {@code 8 - depth}.
     */
    default BlockState getLiquidBlockState(int depth, boolean falling) {
        // Convert effective depth back to inverse decay before adding the falling
        // flag, preserving the property representation expected by liquid storage and supply checks.
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(falling ? 0b1000 | (8 - depth) : 8 - depth));
    }

    /**
     * Returns this liquid type's full-depth state with downward flow encoded.
     */
    default BlockState getFallingBlockState() {
        // Falling columns use the flag without horizontal decay bits, so their
        // effective depth remains full while source recognition can still distinguish them from sources.
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(0b1000));
    }

    /**
     * Returns this liquid type's source state with zero encoded decay.
     */
    default BlockState getSourceBlockState() {
        // Zero encodes a source without the falling flag, which keeps source
        // formation and replenishment checks distinct from equally deep downward-flowing liquid states.
        return getBlockType().ofState(BlockPropertyTypes.LIQUID_DEPTH.createValue(0));
    }

    /**
     * Returns whether this liquid supports containment within another block. The default
     * permits containment; liquid implementations such as lava override this decision before
     * the flow logic considers a receiving block's liquid reaction.
     */
    default boolean canBeContained() {
        return true;
    }

    /**
     * Attempts to replace liquid with a hardened block and reports whether hardening occurs.
     * {@code flownIntoBy} may be {@code null} when the caller requests a surrounding-block
     * check; this default performs no changes and returns {@code false}.
     */
    @ApiStatus.OverrideOnly
    default boolean tryHarden(Block block, Block flownIntoBy) {
        return false;
    }

    /**
     * Returns the effective depth lost by one horizontal flow step in {@code dimensionType}.
     * The liquid update logic subtracts this amount before writing the next liquid state and
     * uses it to bound path exploration.
     */
    int getFlowDecay(DimensionType dimensionType);

    /**
     * Returns the delay in game ticks before this liquid's next scheduled flow update.
     * Implementations may vary the delay with {@code dimensionType}; the block update manager
     * uses the result when scheduling placement and neighbor changes.
     */
    int getFlowSpeed(DimensionType dimensionType);

    /**
     * Returns whether this liquid participates in automatic source formation. The server's
     * liquid update checks for at least two horizontal sources, depth {@code 7}, and a blocked
     * sideways-style flow check below before proposing a source.
     */
    boolean canFormSource();

    /**
     * Returns whether {@code blockType} belongs to this liquid's flow family. Matching
     * includes stationary and flowing variants when the implementation groups them, allowing
     * supply checks and depth updates across those variants.
     */
    boolean isSameLiquidType(BlockType<?> blockType);
}
