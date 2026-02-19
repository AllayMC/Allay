package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

/**
 * Utility class with static helper methods for controlling entity movement
 * and look targets through memory storage.
 *
 * @author daoge_cmd
 */
public final class EntityControlHelper {

    private EntityControlHelper() {
    }

    public static void setRouteTarget(EntityIntelligent entity, Vector3dc target) {
        entity.setMoveTarget(target);
        entity.getBehaviorGroup().setRouteUpdateRequired(true);
    }

    public static void setLookTarget(EntityIntelligent entity, Vector3dc target) {
        entity.setLookTarget(target);
    }

    public static void removeRouteTarget(EntityIntelligent entity) {
        entity.getMemoryStorage().clear(MemoryTypes.MOVE_TARGET);
        entity.setMoveDirectionStart(null);
        entity.setMoveDirectionEnd(null);
    }

    public static void removeLookTarget(EntityIntelligent entity) {
        entity.getMemoryStorage().clear(MemoryTypes.LOOK_TARGET);
    }
}
