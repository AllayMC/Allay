package org.allaymc.api.entity.ai.behavior;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code BehaviorEvaluator} decides whether an entity can start a behavior at
 * the current evaluation point. Evaluators can inspect entity state and shared
 * memories; successful evaluation permits scheduling but does not itself start
 * or execute the behavior.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BehaviorEvaluator {

    /**
     * Returns whether the supplied entity currently satisfies this evaluator's
     * conditions. Composite behaviors may invoke an evaluator more than once
     * during selection, so implementations must account for repeated
     * observations.
     */
    boolean evaluate(EntityIntelligent entity);
}
