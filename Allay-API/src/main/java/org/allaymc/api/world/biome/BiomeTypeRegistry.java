package org.allaymc.api.world.biome;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.AbstractMappedRegistry;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Map;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public interface BiomeTypeRegistry extends AbstractMappedRegistry<BiomeType, BiomeData, Map<BiomeType, BiomeData>> {
    ApiInstanceHolder<BiomeTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static BiomeTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NbtMap getBiomeDefinition();
}
