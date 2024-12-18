package org.allaymc.api.world.generator.function;

import org.allaymc.api.world.generator.WorldGenerator;

/**
 * @author daoge_cmd
 */
public interface GenerateFunction {
    default void init(WorldGenerator generator) {}

    String getName();
}
