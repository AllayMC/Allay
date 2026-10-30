package org.allaymc.api.entity.ai.behavior;

/**
 * {@code Behavior} combines eligibility checks, tick execution, and scheduling
 * metadata for an entity's AI. Behavior groups use larger priorities to select
 * ordinary behaviors, while composite behaviors can use weights to choose
 * between eligible children. The stored state records activation separately
 * from executor return values.
 *
 * @author daoge_cmd
 */
public interface Behavior extends BehaviorExecutor, BehaviorEvaluator {

    /**
     * Returns the priority used when scheduling ordinary behaviors. Larger
     * values take precedence; core behavior scheduling ignores this value,
     * while eligible ordinary behaviors with equal priority may execute
     * together.
     */
    int getPriority();

    /**
     * Returns the weight used by composites for random child selection. The
     * ordinary behavior group does not use this value; weighted selection
     * requires a positive total among eligible children.
     */
    int getWeight();

    /**
     * Returns the number of group ticks between eligibility checks. Running
     * behaviors are not re-evaluated by the default group, and values no
     * greater than one allow evaluation every tick.
     */
    int getPeriod();

    /**
     * Returns the activation state currently stored for this behavior.
     */
    BehaviorState getBehaviorState();

    /**
     * Stores the activation state supplied by the behavior scheduler.
     */
    void setBehaviorState(BehaviorState behaviorState);
}
