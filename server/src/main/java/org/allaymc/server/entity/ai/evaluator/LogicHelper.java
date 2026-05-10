package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;

/**
 * Utility class for composing behavior evaluators with logical operators.
 *
 * @author daoge_cmd
 */
public final class LogicHelper {

    private LogicHelper() {
    }

    /**
     * Create an evaluator that returns {@code true} only if all given evaluators return {@code true}.
     *
     * @param evaluators the evaluators to compose
     * @return the composite evaluator
     */
    public static BehaviorEvaluator all(BehaviorEvaluator... evaluators) {
        return new AllMatchEvaluator(evaluators);
    }

    /**
     * Create an evaluator that returns {@code true} if any given evaluator returns {@code true}.
     *
     * @param evaluators the evaluators to compose
     * @return the composite evaluator
     */
    public static BehaviorEvaluator any(BehaviorEvaluator... evaluators) {
        return new AnyMatchEvaluator(evaluators);
    }
}
