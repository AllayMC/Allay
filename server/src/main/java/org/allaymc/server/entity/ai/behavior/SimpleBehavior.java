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
public class SimpleBehavior extends AbstractBehavior {

    protected final BehaviorExecutor executor;
    protected final BehaviorEvaluator evaluator;
    protected final int priority;
    protected final int weight;
    protected final int period;

    public SimpleBehavior(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight, int period) {
        this.executor = executor;
        this.evaluator = evaluator;
        this.priority = priority;
        this.weight = weight;
        this.period = period;
    }

    public SimpleBehavior(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority, int weight) {
        this(executor, evaluator, priority, weight, 1);
    }

    public SimpleBehavior(BehaviorExecutor executor, BehaviorEvaluator evaluator, int priority) {
        this(executor, evaluator, priority, 1, 1);
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
}
