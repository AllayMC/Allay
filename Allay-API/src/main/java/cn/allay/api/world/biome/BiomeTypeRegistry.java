package cn.allay.api.world.biome;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Map;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public interface BiomeTypeRegistry extends MappedRegistry<String, BiomeType, Map<String, BiomeType>> {
    ApiInstanceHolder<BiomeTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BiomeTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NbtMap getBiomeDefinitionListTag();
}
