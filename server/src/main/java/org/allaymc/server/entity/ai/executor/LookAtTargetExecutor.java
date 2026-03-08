package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeLookTarget;

/**
 * Looks at a position stored in a memory type for a specified duration.
 *
 * @author daoge_cmd
 */
public class LookAtTargetExecutor implements BehaviorExecutor {

    protected final MemoryType<? extends Vector3dc> memoryType;
    protected final int duration;

    protected int tickCounter;

    public LookAtTargetExecutor(MemoryType<? extends Vector3dc> memoryType, int duration) {
        this.memoryType = memoryType;
        this.duration = duration;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.setPitchEnabled(true);
    }

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

    @Override
    public void onStop(EntityIntelligent entity) {
        removeLookTarget(entity);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        removeLookTarget(entity);
    }
}
