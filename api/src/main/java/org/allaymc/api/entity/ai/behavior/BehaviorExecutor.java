package org.allaymc.api.entity.ai.behavior;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code BehaviorExecutor} supplies active behavior work and lifecycle
 * callbacks for intelligent entities. The default group starts a behavior
 * before its first execution, keeps it running while execution returns {@code
 * true}, and distinguishes normal stopping from priority interruption.
 *
 * @author daoge_cmd
 */
public interface BehaviorExecutor {

    /**
     * Runs one active tick and returns whether execution should continue. The
     * default group invokes {@code onStop} when this returns {@code false};
     * returning {@code true} retains the running behavior.
     */
    boolean execute(EntityIntelligent entity);

    /**
     * Handles interruption before the group marks this behavior stopped.
     * Ordinary behaviors receive this callback when a strictly higher priority
     * replaces them; the default implementation performs no cleanup.
     */
    default void onInterrupt(EntityIntelligent entity) {
    }

    /**
     * Handles activation before the group marks this behavior active. The group
     * can execute the behavior later in the same tick, and the default
     * implementation performs no setup.
     */
    default void onStart(EntityIntelligent entity) {
    }

    /**
     * Handles normal completion after execution returns {@code false}. The
     * group marks the behavior stopped and removes it afterward; the default
     * implementation performs no cleanup for completed behavior state.
     */
    default void onStop(EntityIntelligent entity) {
    }
}
