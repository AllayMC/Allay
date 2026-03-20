package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

/**
 * Looks at an entity whose runtime ID is stored in memory for a duration.
 *
 * @author daoge_cmd
 */
public class LookAtEntityExecutor implements BehaviorExecutor {

    protected final MemoryType<Long> entityIdMemory;
    protected final int duration;

    protected int tickCounter;

    public LookAtEntityExecutor(MemoryType<Long> entityIdMemory, int duration) {
        this.entityIdMemory = entityIdMemory;
        this.duration = duration;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        if (tickCounter > duration) return false;

        var targetId = entity.getMemoryStorage().get(entityIdMemory);
        if (targetId == null) return false;

        var targetEntity = entity.getDimension().getEntityManager().getEntity(targetId);
        if (targetEntity == null) return false;

        var targetLoc = targetEntity.getLocation();
        EntityControlHelper.setLookTarget(entity, new Vector3d(
                targetLoc.x(), targetLoc.y() + targetEntity.getEyeHeight(), targetLoc.z()
        ));

        return true;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeLookTarget(entity);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
