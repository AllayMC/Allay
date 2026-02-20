package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Evaluates walkable positions for ground entities.
 * Avoids lava, cactus, and other hazardous blocks.
 * Treats water as a valid standing surface.
 *
 * @author daoge_cmd
 */
public class WalkingPosEvaluator implements GroundPosEvaluator {

    @Override
    public boolean evaluate(EntityIntelligent entity, Block block) {
        var blockType = block.getBlockType();

        // Avoid lava
        if (blockType.hasBlockTag(BlockTags.LAVA)) return false;

        // Avoid cactus
        if (blockType == BlockTypes.CACTUS) return false;

        // Water is a valid standing surface
        if (blockType.hasBlockTag(BlockTags.WATER)) return true;

        // For other blocks, delegate to physics: checks solid ground + AABB passability
        var pos = block.getPosition();
        return entity.canStandSafely(pos.x(), pos.y() + 1, pos.z(), pos.dimension());
    }
}
