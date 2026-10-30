package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.function.ToLongFunction;


/**
 * {@code PassByTimeEvaluator} tests an inclusive elapsed-time range relative to
 * an entity tick timestamp. A supplied function or timed memory provides the
 * timestamp; negative timestamps disable eligibility, while valid timestamps
 * are subtracted from the entity's current tick without conversion to
 * wall-clock time.
 *
 * @author daoge_cmd
 */
public class PassByTimeEvaluator implements BehaviorEvaluator {

    /**
     * Entity tick timestamp provider.
     */
    protected final ToLongFunction<EntityIntelligent> timeSupplier;
    /**
     * Inclusive minimum elapsed ticks.
     */
    protected final long min;
    /**
     * Inclusive maximum elapsed ticks.
     */
    protected final long max;

    /**
     * Creates an elapsed-tick evaluator reading the supplied memory key. A
     * {@code null} memory value becomes a negative sentinel and fails
     * eligibility; the supplied minimum and maximum are inclusive.
     */
    public PassByTimeEvaluator(MemoryType<Long> timedMemory, long min, long max) {
        this(entity -> {
            var lastTime = entity.getMemoryStorage().get(timedMemory);
            // Missing timestamps cannot establish elapsed time, so the negative sentinel
            // keeps an uninitialized event from making this behavior eligible during evaluation.
            return lastTime == null ? -1L : lastTime;
        }, min, max);
    }

    /**
     * Creates an elapsed-tick evaluator retaining the supplied timestamp
     * provider and bounds. Negative provider results fail eligibility; callers
     * must use timestamps measured against the same entity tick clock.
     */
    public PassByTimeEvaluator(ToLongFunction<EntityIntelligent> timeSupplier, long min, long max) {
        this.timeSupplier = timeSupplier;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns whether elapsed entity ticks fall within both inclusive bounds. A
     * negative timestamp returns {@code false}, while future timestamps produce
     * negative elapsed values and are compared with the supplied bounds.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        var lastTime = timeSupplier.applyAsLong(entity);
        if (lastTime < 0) return false;
        long elapsed = entity.getTick() - lastTime;
        return elapsed >= min && elapsed <= max;
    }
}
