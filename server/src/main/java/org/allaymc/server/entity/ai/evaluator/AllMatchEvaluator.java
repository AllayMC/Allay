package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code AllMatchEvaluator} combines behavior eligibility checks using
 * all-match logic. Evaluation visits the retained array in order and stops when
 * its result is determined, so later evaluators can be skipped and mutations to
 * the original array affect subsequent checks.
 *
 * @author daoge_cmd
 */
public class AllMatchEvaluator implements BehaviorEvaluator {

    /**
     * {@code evaluators} retains the caller's array without copying it. Its
     * order controls short-circuit evaluation, and later caller mutations
     * change the checks performed.
     */
    protected final BehaviorEvaluator[] evaluators;

    /**
     * Creates a composite retaining the supplied evaluator array directly.
     * Callers must provide usable entries and coordinate any later array
     * mutations with evaluation because no defensive copy is made.
     */
    public AllMatchEvaluator(BehaviorEvaluator... evaluators) {
        this.evaluators = evaluators;
    }

    /**
     * Returns whether all retained evaluators accept the entity. Evaluation
     * stops at the first {@code false} result; an empty array returns {@code
     * true} without invoking any child evaluator.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        for (var evaluator : evaluators) {
            if (!evaluator.evaluate(entity)) {
                return false;
            }
        }
        return true;
    }
}
