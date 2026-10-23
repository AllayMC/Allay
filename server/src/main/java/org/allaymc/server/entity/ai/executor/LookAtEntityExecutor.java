package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

/**
 * {@code LookAtEntityExecutor} maintains a look target for a bounded number of
 * executions. A runtime ID resolves the target entity on each tick, and the
 * look point includes its eye height. Missing target data ends the behavior
 * early, and both normal completion and interruption clear the look memory
 * consumed by orientation controllers.
 *
 * @author daoge_cmd
 */
public class LookAtEntityExecutor implements BehaviorExecutor {

    /**
     * Look target source memory key.
     */
    protected final MemoryType<Long> entityIdMemory;
    /**
     * Maximum look execution ticks.
     */
    protected final int duration;

    /**
     * Execution ticks since activation.
     */
    protected int tickCounter;

    /**
     * Creates a timed look executor retaining its source memory key and
     * duration.
     */
    public LookAtEntityExecutor(MemoryType<Long> entityIdMemory, int duration) {
        this.entityIdMemory = entityIdMemory;
        this.duration = duration;
    }

    /**
     * Resets the look timer before this behavior begins executing again.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
    }

    /**
     * Updates the look memory while the incremented timer remains within its
     * duration. The runtime ID must resolve to an entity in the current
     * dimension. The method returns {@code false} on timeout or missing target
     * data, leaving cleanup to the stop callback.
     */
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

    /**
     * Clears the look target memory used by entity orientation controllers.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeLookTarget(entity);
    }

    /**
     * Clears the look target memory used by entity orientation controllers.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
