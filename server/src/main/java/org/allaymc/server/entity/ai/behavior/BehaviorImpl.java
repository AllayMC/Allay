package org.allaymc.server.entity.ai.behavior;

import lombok.Getter;
import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code BehaviorImpl} pairs one evaluator with one executor and exposes
 * immutable scheduling metadata. Evaluation delegates to {@code evaluator},
 * while execution and lifecycle callbacks delegate to {@code executor}; the
 * constructor and builder retain both collaborators without validation or
 * copying.
 *
 * @author daoge_cmd
 */
@Getter
public class BehaviorImpl extends AbstractBehavior {

    /**
     * Retained behavior execution delegate.
     */
    protected final BehaviorExecutor executor;
    /**
     * Retained behavior eligibility delegate.
     */
    protected final BehaviorEvaluator evaluator;
    /**
     * Ordinary scheduler priority value.
     */
    protected final int priority;
    /**
     * Composite random selection weight.
     */
    protected final int weight;
    /**
     * Eligibility check interval in ticks.
     */
    protected final int period;

    /**
     * Creates a behavior retaining the supplied delegates and scheduling
     * values. This constructor performs no validation; callers must supply
     * usable delegates and scheduling metadata accepted by their behavior group
     * or composite.
     */
    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight, int period) {
        this.executor = executor;
        this.evaluator = evaluator;
        this.priority = priority;
        this.weight = weight;
        this.period = period;
    }

    /**
     * Creates a behavior with the supplied weight and a one-tick evaluation
     * interval. The constructor retains the delegates directly and leaves
     * validation to their consumers during later use.
     */
    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight) {
        this(executor, evaluator, priority, weight, 1);
    }

    /**
     * Creates a behavior with unit weight and a one-tick evaluation interval.
     * The constructor retains both delegates directly and preserves the
     * supplied priority for ordinary scheduling or composite selection.
     */
    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority) {
        this(executor, evaluator, priority, 1, 1);
    }

    /**
     * Returns a fresh builder for configuring a delegated AI behavior.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns whether the retained evaluator accepts the supplied intelligent
     * entity.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        return evaluator.evaluate(entity);
    }

    /**
     * Returns whether the retained executor should continue for this entity.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        return executor.execute(entity);
    }

    /**
     * Forwards interruption cleanup to the executor for the supplied entity.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        executor.onInterrupt(entity);
    }

    /**
     * Forwards behavior activation to the executor for the supplied entity.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        executor.onStart(entity);
    }

    /**
     * Forwards normal completion to the executor for the supplied entity.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        executor.onStop(entity);
    }

    /**
     * {@code Builder} collects delegates and scheduling metadata for a {@code
     * BehaviorImpl}.
     */
    public static class Builder {
        /**
         * Selected behavior execution delegate.
         */
        private BehaviorExecutor executor;
        /**
         * Selected behavior eligibility delegate.
         */
        private BehaviorEvaluator evaluator;
        /**
         * Selected ordinary scheduler priority.
         */
        private int priority;
        /**
         * Selected composite random weight.
         */
        private int weight = 1;
        /**
         * Selected evaluation interval in ticks.
         */
        private int period = 1;

        /**
         * Stores the execution delegate and returns this mutable behavior
         * builder.
         */
        public Builder executor(BehaviorExecutor executor) {
            this.executor = executor;
            return this;
        }

        /**
         * Stores the eligibility delegate and returns this mutable behavior
         * builder.
         */
        public Builder evaluator(BehaviorEvaluator evaluator) {
            this.evaluator = evaluator;
            return this;
        }

        /**
         * Stores the scheduling priority and returns this mutable behavior
         * builder.
         */
        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Stores the selection weight and returns this mutable behavior
         * builder.
         */
        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        /**
         * Stores the evaluation interval and returns this mutable behavior
         * builder.
         */
        public Builder period(int period) {
            this.period = period;
            return this;
        }

        /**
         * Creates a behavior from the currently collected values without
         * validation. Unspecified delegates remain {@code null}, priority
         * defaults to zero, and weight and evaluation period each default to
         * one.
         */
        public BehaviorImpl build() {
            return new BehaviorImpl(executor, evaluator, priority, weight, period);
        }
    }
}
