package org.allaymc.server.entity.ai.evaluator;

import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ProbabilityEvaluator} makes a fresh bounded random eligibility
 * decision on every evaluation. The numerator and random bound are retained
 * without validation; ordinary probabilities use a positive bound and a
 * numerator between zero and that bound inclusive.
 *
 * @author daoge_cmd
 */
public class ProbabilityEvaluator implements BehaviorEvaluator {

    /**
     * Successful random interval size.
     */
    protected final int probability;
    /**
     * Exclusive random sampling bound.
     */
    protected final int total;

    /**
     * Creates a random evaluator retaining the numerator and bound unchanged. A
     * nonpositive bound fails during evaluation; nonpositive numerators never
     * succeed, while numerators at least as large as a positive bound always
     * succeed.
     */
    public ProbabilityEvaluator(int probability, int total) {
        this.probability = probability;
        this.total = total;
    }

    /**
     * Returns whether a fresh random sample is below the configured numerator.
     * {@code total} must be positive, otherwise sampling throws {@code
     * IllegalArgumentException}; the supplied entity does not affect the draw.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        return ThreadLocalRandom.current().nextInt(total) < probability;
    }
}
