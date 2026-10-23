package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3ic;

/**
 * {@code BlockCheckEvaluator} tests a block type at an integer offset from the
 * entity's floored position. The configured offset is retained directly, so
 * eligibility reflects both the current block state and any changes to a
 * mutable offset supplied by the caller.
 *
 * @author daoge_cmd
 */
public class BlockCheckEvaluator implements BehaviorEvaluator {

    /**
     * Expected registered block type.
     */
    protected final BlockType<?> blockType;
    /**
     * {@code offset} retains the supplied block-coordinate displacement. A
     * mutable vector passed through this read-only interface can change later
     * evaluation positions without replacing the evaluator.
     */
    protected final Vector3ic offset;

    /**
     * Creates a block evaluator retaining the expected type and supplied
     * offset.
     */
    public BlockCheckEvaluator(BlockType<?> blockType, Vector3ic offset) {
        this.blockType = blockType;
        this.offset = offset;
    }

    /**
     * Returns whether the offset block has the configured type by identity.
     * Coordinates are floored before the integer offset is added, and a missing
     * block state returns {@code false}.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        var loc = entity.getLocation();
        // Floor before adding offsets so negative world coordinates select their containing block.
        int x = (int) Math.floor(loc.x()) + offset.x();
        int y = (int) Math.floor(loc.y()) + offset.y();
        int z = (int) Math.floor(loc.z()) + offset.z();
        var blockState = entity.getDimension().getBlockState(x, y, z);
        return blockState != null && blockState.getBlockType() == blockType;
    }
}
