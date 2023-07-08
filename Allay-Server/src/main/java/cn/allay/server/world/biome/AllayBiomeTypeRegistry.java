package cn.allay.server.world.biome;

import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.MutableNbtMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayBiomeTypeRegistry extends SimpleMappedRegistry<String, BiomeType, Map<String, BiomeType>> implements BiomeTypeRegistry {

    private MutableNbtMap biomeDefinition;

    public AllayBiomeTypeRegistry() {
        super(null, input -> new HashMap<>());
        loadVanillaBiomeDefinition();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinition() {
        try (InputStream stream = AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("biome_definitions_full.nbt")) {
            assert stream != null;
            biomeDefinition = MutableNbtMap.from((NbtMap) NbtUtils.createNetworkReader(stream).readTag());
        }
    }

    @Override
    public NbtMap getBiomeDefinition() {
        return biomeDefinition.build();
    }
}
