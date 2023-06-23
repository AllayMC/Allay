package cn.allay.api.world.biome;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;

import java.util.Map;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public interface BiomeRegistry extends MappedRegistry<Identifier, Class<? extends Biome>, Map<Identifier, Class<? extends Biome>>> {
    //TODO: Register vanilla biomes
    ApiInstanceHolder<BiomeRegistry> REGISTRY = ApiInstanceHolder.of();

    static BiomeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NbtMap getBiomeDefinitionListTag();
}
