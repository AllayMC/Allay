package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

/**
 * {@code SpacePosEvaluator} decides whether a world position is suitable for
 * spatial movement by an intelligent entity. The space finder invokes this
 * check after rejecting missing or colliding candidate blocks, so
 * implementations can add flying or swimming constraints for the remaining cell
 * centers.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface SpacePosEvaluator {

    /**
     * Returns whether the supplied spatial position suits this entity's
     * movement rules.
     */
    boolean evaluate(EntityIntelligent entity, Vector3dc pos);
}
