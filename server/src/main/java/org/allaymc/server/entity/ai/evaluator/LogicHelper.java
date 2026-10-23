package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;

/**
 * {@code LogicHelper} constructs ordered logical combinations of behavior
 * evaluators. The returned composites retain the supplied arrays and
 * short-circuit evaluation, allowing behavior definitions to share the same
 * eligibility checks while choosing all-match or any-match semantics.
 *
 * @author daoge_cmd
 */
public final class LogicHelper {

    /**
     * Prevents instantiation of the behavior evaluator composition helper
     * holder.
     */
    private LogicHelper() {
    }

    /**
     * Creates an ordered all-match composite retaining the supplied array. The
     * composite stops at the first {@code false} result and returns {@code
     * true} when the array contains no evaluators.
     */
    public static BehaviorEvaluator all(BehaviorEvaluator... evaluators) {
        return new AllMatchEvaluator(evaluators);
    }

    /**
     * Creates an ordered any-match composite retaining the supplied array. The
     * composite stops at the first {@code true} result and returns {@code
     * false} when the array contains no evaluators.
     */
    public static BehaviorEvaluator any(BehaviorEvaluator... evaluators) {
        return new AnyMatchEvaluator(evaluators);
    }
}
