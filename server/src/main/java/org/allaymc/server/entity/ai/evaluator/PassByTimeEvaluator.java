package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.function.ToLongFunction;


/**
 * Checks if the time elapsed since a timed value is within
 * the range {@code [min, max]}.
 *
 * @author daoge_cmd
 */
public class PassByTimeEvaluator implements BehaviorEvaluator {

    protected final ToLongFunction<EntityIntelligent> timeSupplier;
    protected final long min;
    protected final long max;

    public PassByTimeEvaluator(MemoryType<Long> timedMemory, long min, long max) {
        this(entity -> {
            var lastTime = entity.getMemoryStorage().get(timedMemory);
            return lastTime == null ? -1L : lastTime;
        }, min, max);
    }

    public PassByTimeEvaluator(ToLongFunction<EntityIntelligent> timeSupplier, long min, long max) {
        this.timeSupplier = timeSupplier;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean evaluate(EntityIntelligent entity) {
        var lastTime = timeSupplier.applyAsLong(entity);
        if (lastTime < 0) return false;
        long elapsed = entity.getTick() - lastTime;
        return elapsed >= min && elapsed <= max;
    }
}
