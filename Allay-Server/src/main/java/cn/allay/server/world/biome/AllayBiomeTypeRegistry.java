package cn.allay.server.world.biome;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.api.world.biome.VanillaBiomeTypes;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayBiomeTypeRegistry extends SimpleMappedRegistry<String, BiomeType, Map<String, BiomeType>> implements BiomeTypeRegistry {

    private NbtMap biomeDefinitionListTag;

    public AllayBiomeTypeRegistry() {
        super(null, input -> new HashMap<>());
        //Load vanilla biome type constants
        BiomeType ocean = VanillaBiomeTypes.OCEAN;
        loadVanillaBiomeDefinitionListTag();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinitionListTag() {
        //TODO: Support custom biome
        biomeDefinitionListTag = (NbtMap) NbtUtils.createNetworkReader(AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("biome_definitions_full.nbt")).readTag();
    }

    @Override
    public NbtMap getBiomeDefinitionListTag() {
        return biomeDefinitionListTag;
    }
}
