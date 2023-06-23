package cn.allay.server.world.biome;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.world.biome.Biome;
import cn.allay.api.world.biome.BiomeRegistry;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public class AllayBiomeRegistry extends SimpleMappedRegistry<Identifier, Class<? extends Biome>, Map<Identifier, Class<? extends Biome>>> implements BiomeRegistry {

    private NbtMap biomeDefinitionListTag;

    public AllayBiomeRegistry() {
        super(null, input -> new HashMap<>());
        loadVanillaBiomeDefinitionListTag();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinitionListTag() {
        //TODO: support custom biome
        biomeDefinitionListTag = (NbtMap) NbtUtils.createNetworkReader(AllayBiomeRegistry.class.getClassLoader().getResourceAsStream("biome_definitions.json")).readTag();
    }

    @Override
    public NbtMap getBiomeDefinitionListTag() {
        return biomeDefinitionListTag;
    }
}
