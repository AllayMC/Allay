package org.allaymc.api.world.generator;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.AbstractMappedRegistry;

import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
public interface WorldGeneratorFactory extends AbstractMappedRegistry<String, Function<String, WorldGenerator>, Map<String, Function<String, WorldGenerator>>> {
    ApiInstanceHolder<WorldGeneratorFactory> FACTORY = ApiInstanceHolder.create();

    static WorldGeneratorFactory getFactory() {
        return FACTORY.get();
    }

    default WorldGenerator createWorldGenerator(String type, String preset) {
        return this.get(type).apply(preset);
    }
}
