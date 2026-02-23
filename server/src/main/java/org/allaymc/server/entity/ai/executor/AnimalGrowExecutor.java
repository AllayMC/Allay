package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Simple executor that grows a baby animal into an adult.
 *
 * @author daoge_cmd
 */
public class AnimalGrowExecutor implements BehaviorExecutor {

    @Override
    public boolean execute(EntityIntelligent entity) {
        if (entity instanceof EntityAnimal animal) {
            animal.setBaby(false);
        }
        return false;
    }
}
