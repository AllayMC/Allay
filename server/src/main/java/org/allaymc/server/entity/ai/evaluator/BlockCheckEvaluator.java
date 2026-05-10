package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3ic;

/**
 * Checks if the block at the entity's location plus an offset
 * matches the expected block type.
 *
 * @author daoge_cmd
 */
public class BlockCheckEvaluator implements BehaviorEvaluator {

    protected final BlockType<?> blockType;
    protected final Vector3ic offset;

    public BlockCheckEvaluator(BlockType<?> blockType, Vector3ic offset) {
        this.blockType = blockType;
        this.offset = offset;
    }

    @Override
    public boolean evaluate(EntityIntelligent entity) {
        var loc = entity.getLocation();
        int x = (int) Math.floor(loc.x()) + offset.x();
        int y = (int) Math.floor(loc.y()) + offset.y();
        int z = (int) Math.floor(loc.z()) + offset.z();
        var blockState = entity.getDimension().getBlockState(x, y, z);
        return blockState != null && blockState.getBlockType() == blockType;
    }
}
