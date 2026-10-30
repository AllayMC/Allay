package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code AnimalGrowExecutor} turns an intelligent animal into an adult during a
 * single behavior execution. The executor performs no age or timing check
 * itself, so its surrounding evaluator decides when growth is due and
 * non-animal entities simply complete without a change.
 *
 * @author daoge_cmd
 */
public class AnimalGrowExecutor implements BehaviorExecutor {

    /**
     * Marks an animal as adult and completes this behavior immediately.
     * Non-animal intelligent entities receive no update; this method always
     * returns {@code false} so the group can stop the behavior.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        if (entity instanceof EntityAnimal animal) {
            animal.setBaby(false);
        }
        return false;
    }
}
