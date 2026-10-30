package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code GroundPosEvaluator} decides whether a support block is suitable for an
 * intelligent entity's ground route. The ground finder passes the block beneath
 * a candidate standing level, allowing implementations to consider hazards,
 * collision shapes, and the entity's ability to stand there.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface GroundPosEvaluator {

    /**
     * Returns whether the supplied support block permits this entity to stand
     * there.
     */
    boolean evaluate(EntityIntelligent entity, Block block);
}
