package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

/**
 * Follows an entity whose runtime ID is stored in memory.
 *
 * @author daoge_cmd
 */
public class FollowEntityExecutor implements BehaviorExecutor {

    protected final MemoryType<Long> entityIdMemory;
    protected final float speed;
    protected final double maxRangeSq;
    protected final double minRangeSq;

    protected Vector3d lastTargetPos;

    public FollowEntityExecutor(MemoryType<Long> entityIdMemory, float speed, double maxRangeSq, double minRangeSq) {
        this.entityIdMemory = entityIdMemory;
        this.speed = speed;
        this.maxRangeSq = maxRangeSq;
        this.minRangeSq = minRangeSq;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        entity.setMovementSpeed(speed);
        lastTargetPos = null;
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        var targetId = entity.getMemoryStorage().get(entityIdMemory);
        if (targetId == null) return false;

        var targetEntity = entity.getDimension().getEntityManager().getEntity(targetId);
        if (targetEntity == null) return false;

        var targetLoc = targetEntity.getLocation();
        double distSq = entity.getLocation().distanceSquared(targetLoc);

        if (distSq > maxRangeSq) return false;

        if (distSq > minRangeSq) {
            var targetPos = new Vector3d(targetLoc.x(), targetLoc.y(), targetLoc.z());
            // Re-path only when target moved >1 block
            if (lastTargetPos == null || lastTargetPos.distanceSquared(targetPos) > 1.0) {
                EntityControlHelper.setRouteTarget(entity, targetPos);
                lastTargetPos = targetPos;
            }
        } else {
            EntityControlHelper.removeRouteTarget(entity);
        }

        EntityControlHelper.setLookTarget(entity, new Vector3d(
                targetLoc.x(), targetLoc.y() + targetEntity.getEyeHeight(), targetLoc.z()
        ));

        return true;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
