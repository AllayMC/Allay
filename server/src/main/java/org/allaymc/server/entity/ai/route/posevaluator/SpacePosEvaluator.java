package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

/**
 * Evaluates 3D positions during pathfinding to determine their suitability for movement.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface SpacePosEvaluator {

    /**
     * Evaluate a 3D position for movement suitability (e.g., flying or swimming).
     *
     * @param entity the entity
     * @param pos    the position to evaluate
     * @return {@code true} if the position is suitable
     */
    boolean evaluate(EntityIntelligent entity, Vector3dc pos);
}
