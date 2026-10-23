package org.allaymc.server.entity.ai.behavior;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.behavior.BehaviorState;

/**
 * {@code AbstractBehavior} supplies the mutable activation state shared by
 * built-in behavior implementations. New instances start stopped; the behavior
 * group changes the state after lifecycle callbacks, while subclasses provide
 * scheduling metadata, eligibility evaluation, and active execution.
 *
 * @author daoge_cmd
 */
public abstract class AbstractBehavior implements Behavior {

    /**
     * {@code behaviorState} records scheduler activation and initially equals
     * {@code STOP}. The generated setter changes this value without running any
     * lifecycle callbacks.
     */
    @Getter
    @Setter
    protected BehaviorState behaviorState = BehaviorState.STOP;
}
