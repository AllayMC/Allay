package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.ai.route.PosEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Evaluates walkable positions for ground entities.
 * Avoids lava, cactus, and other hazardous blocks.
 *
 * @author daoge_cmd
 */
public class WalkingPosEvaluator implements PosEvaluator {

    @Override
    public boolean evalStandingBlock(EntityIntelligent entity, Block block) {
        var blockType = block.getBlockType();

        // Avoid lava
        if (blockType.hasBlockTag(BlockTags.LAVA)) {
            return false;
        }

        // Avoid cactus
        if (blockType == BlockTypes.CACTUS) {
            return false;
        }

        return true;
    }
}
