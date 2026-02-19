package org.allaymc.api.entity.ai.route;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

/**
 * Evaluates positions during pathfinding to determine their suitability.
 *
 * @author daoge_cmd
 */
public interface PosEvaluator {

    /**
     * Evaluate a 3D position for movement suitability (e.g., flying or swimming).
     *
     * @param entity the entity
     * @param pos    the position to evaluate
     * @return {@code true} if the position is suitable
     */
    default boolean evalPos(EntityIntelligent entity, Vector3dc pos) {
        return true;
    }

    /**
     * Evaluate a standing block for ground walking suitability.
     *
     * @param entity the entity
     * @param block  the block being evaluated
     * @return {@code true} if the entity can stand on / walk through this block
     */
    default boolean evalStandingBlock(EntityIntelligent entity, Block block) {
        return true;
    }
}
