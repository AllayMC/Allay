package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;


/**
 * Checks if the time elapsed since a timed memory value is within
 * the range {@code [min, max]}.
 *
 * @author daoge_cmd
 */
public class PassByTimeEvaluator implements BehaviorEvaluator {

    protected final MemoryType<Long> timedMemory;
    protected final long min;
    protected final long max;

    public PassByTimeEvaluator(MemoryType<Long> timedMemory, long min, long max) {
        this.timedMemory = timedMemory;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean evaluate(EntityIntelligent entity) {
        var lastTime = entity.getMemoryStorage().get(timedMemory);
        if (lastTime == null || lastTime < 0) return false;
        long elapsed = entity.getTick() - lastTime;
        return elapsed >= min && elapsed <= max;
    }
}
