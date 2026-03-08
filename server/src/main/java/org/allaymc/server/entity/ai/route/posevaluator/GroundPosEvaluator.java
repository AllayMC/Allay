package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Evaluates ground blocks during pathfinding to determine their suitability for standing.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface GroundPosEvaluator {

    /**
     * Evaluate a standing block for ground walking suitability.
     *
     * @param entity the entity
     * @param block  the block being evaluated
     * @return {@code true} if the entity can stand on this block
     */
    boolean evaluate(EntityIntelligent entity, Block block);
}
