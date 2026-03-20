package org.allaymc.server.entity.component.animal;

import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;

/**
 * @author daoge_cmd
 */
public class EntityAnimalPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    @Override
    public double getStepHeight() {
        return 0.5;
    }
}
