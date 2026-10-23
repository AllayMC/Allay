package org.allaymc.server.entity.component.animal;

import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;

/**
 * {@code EntityAnimalPhysicsComponentImpl} supplies animal movement with a
 * half-block step height. The inherited physics implementation uses this height
 * when trying to move over an obstacle, while retaining its ordinary gravity,
 * collision, and liquid-motion behavior.
 *
 * @author daoge_cmd
 */
public class EntityAnimalPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    /**
     * Returns the half-block step height used for ordinary animal movement. The
     * inherited collision solver uses this value when attempting to step over
     * obstacles rather than requiring an explicit jump from AI.
     */
    @Override
    public double getStepHeight() {
        return 0.5;
    }
}
