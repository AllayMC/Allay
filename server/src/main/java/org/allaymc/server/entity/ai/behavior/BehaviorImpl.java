package org.allaymc.server.entity.ai.behavior;

import lombok.Getter;
import org.allaymc.api.entity.ai.behavior.BehaviorEvaluator;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * A simple behavior that delegates evaluation and execution to separate
 * {@link BehaviorEvaluator} and {@link BehaviorExecutor} instances.
 *
 * @author daoge_cmd
 */
@Getter
public class BehaviorImpl extends AbstractBehavior {

    protected final BehaviorExecutor executor;
    protected final BehaviorEvaluator evaluator;
    protected final int priority;
    protected final int weight;
    protected final int period;

    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight, int period) {
        this.executor = executor;
        this.evaluator = evaluator;
        this.priority = priority;
        this.weight = weight;
        this.period = period;
    }

    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight) {
        this(executor, evaluator, priority, weight, 1);
    }

    public BehaviorImpl(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority) {
        this(executor, evaluator, priority, 1, 1);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean evaluate(EntityIntelligent entity) {
        return evaluator.evaluate(entity);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        return executor.execute(entity);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        executor.onInterrupt(entity);
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        executor.onStart(entity);
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        executor.onStop(entity);
    }

    public static class Builder {
        private BehaviorExecutor executor;
        private BehaviorEvaluator evaluator;
        private int priority;
        private int weight = 1;
        private int period = 1;

        public Builder executor(BehaviorExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder evaluator(BehaviorEvaluator evaluator) {
            this.evaluator = evaluator;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder period(int period) {
            this.period = period;
            return this;
        }

        public BehaviorImpl build() {
            return new BehaviorImpl(executor, evaluator, priority, weight, period);
        }
    }
}
