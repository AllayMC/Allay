package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Checks if a specific memory type has a value stored (is not empty).
 *
 * @author daoge_cmd
 */
public class MemoryCheckNotEmptyEvaluator implements BehaviorEvaluator {

    protected final MemoryType<?> type;

    public MemoryCheckNotEmptyEvaluator(MemoryType<?> type) {
        this.type = type;
    }

    @Override
    public boolean evaluate(EntityIntelligent entity) {
        return entity.getMemoryStorage().notEmpty(type);
    }
}
