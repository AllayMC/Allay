package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code MemoryCheckNotEmptyEvaluator} tests whether a memory key already holds
 * a nonempty stored value. The check delegates to storage emptiness rather than
 * a typed read, so an absent key's default supplier is not invoked to establish
 * eligibility.
 *
 * @author daoge_cmd
 */
public class MemoryCheckNotEmptyEvaluator implements BehaviorEvaluator {

    /**
     * Memory key checked for data.
     */
    protected final MemoryType<?> type;

    /**
     * Creates an evaluator for the supplied shared memory entry key.
     */
    public MemoryCheckNotEmptyEvaluator(MemoryType<?> type) {
        this.type = type;
    }

    /**
     * Returns whether storage already holds nonempty data for the configured
     * key. An absent entry or explicit {@code null} is ineligible, even when a
     * typed read could supply a non-{@code null} default.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        return entity.getMemoryStorage().notEmpty(type);
    }
}
