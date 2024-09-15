package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.context.EntitySpawnContext;

/**
 * @author daoge_cmd
 */
public interface EntitySpawner extends GenerateFunction {
    boolean apply(EntitySpawnContext entitySpawnContext);
}
