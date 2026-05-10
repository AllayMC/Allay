package org.allaymc.api.entity.ai.behavior;

/**
 * A behavior combines an evaluator and an executor with priority-based scheduling.
 *
 * @author daoge_cmd
 */
public interface Behavior extends BehaviorExecutor, BehaviorEvaluator {

    /**
     * Get the priority of this behavior. Higher values indicate higher priority.
     *
     * @return the priority
     */
    int getPriority();

    /**
     * Get the weight of this behavior for weighted random selection among
     * behaviors with the same priority.
     *
     * @return the weight
     */
    int getWeight();

    /**
     * Get the evaluation period in ticks. The behavior is only re-evaluated
     * every {@code period} ticks.
     *
     * @return the period in ticks
     */
    int getPeriod();

    /**
     * Get the current state of this behavior.
     *
     * @return the behavior state
     */
    BehaviorState getBehaviorState();

    /**
     * Set the current state of this behavior.
     *
     * @param behaviorState the new state
     */
    void setBehaviorState(BehaviorState behaviorState);
}
