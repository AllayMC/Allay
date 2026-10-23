package org.allaymc.api.entity.ai.behavior;

/**
 * {@code BehaviorState} identifies whether the scheduler considers a behavior
 * active or stopped. The default group updates this state after lifecycle
 * callbacks, so a callback observes the previous state until the scheduler
 * completes that transition.
 *
 * @author daoge_cmd
 */
public enum BehaviorState {
    /**
     * Scheduled active behavior state.
     */
    ACTIVE,
    /**
     * Unscheduled or completed behavior state.
     */
    STOP
}
