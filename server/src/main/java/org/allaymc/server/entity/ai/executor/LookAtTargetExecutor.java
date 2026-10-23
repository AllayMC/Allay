package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeLookTarget;

/**
 * {@code LookAtTargetExecutor} maintains a look target for a bounded number of
 * executions. The configured position memory supplies the look point directly,
 * and activation enables pitch. Missing target data ends the behavior early,
 * and both normal completion and interruption clear the look memory consumed by
 * orientation controllers.
 *
 * @author daoge_cmd
 */
public class LookAtTargetExecutor implements BehaviorExecutor {

    /**
     * Look target source memory key.
     */
    protected final MemoryType<? extends Vector3dc> memoryType;
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
    public LookAtTargetExecutor(MemoryType<? extends Vector3dc> memoryType, int duration) {
        this.memoryType = memoryType;
        this.duration = duration;
    }

    /**
     * Resets the look timer and enables pitch for target orientation.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.setPitchEnabled(true);
    }

    /**
     * Updates the look memory while the incremented timer remains within its
     * duration. The position reference is retained directly rather than copied.
     * The method returns {@code false} on timeout or missing target data,
     * leaving cleanup to the stop callback.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        if (tickCounter > duration) {
            return false;
        }

        var target = entity.getMemoryStorage().get(memoryType);
        if (target == null) {
            return false;
        }

        EntityControlHelper.setLookTarget(entity, target);
        return true;
    }

    /**
     * Clears the look target memory used by entity orientation controllers.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        removeLookTarget(entity);
    }

    /**
     * Clears the look target memory used by entity orientation controllers.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        removeLookTarget(entity);
    }
}
