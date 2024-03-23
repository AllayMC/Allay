package org.allaymc.server.world.biome;

import lombok.SneakyThrows;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.cloudburstmc.nbt.MutableNbtMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayBiomeTypeRegistry extends SimpleMappedRegistry<BiomeType, BiomeData, Map<BiomeType, BiomeData>> implements BiomeTypeRegistry {

    // TODO: 暂时不知道为什么MutableNbtMap存储的信息转换回NbtMap后部分群系客户端不认
    // 此vanillaBiomeDefinition仅用于发包，并将在解决上述问题后被移除
    private NbtMap vanillaBiomeDefinition;
    private MutableNbtMap biomeDefinition;

    public AllayBiomeTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
        loadVanillaBiomeDefinition();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinition() {
        try (InputStream stream = AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("biome_definitions.nbt")) {
            assert stream != null;
            vanillaBiomeDefinition = (NbtMap) NbtUtils.createGZIPReader(stream).readTag();
            biomeDefinition = MutableNbtMap.from(vanillaBiomeDefinition);
            int i = 0;
            for (var biome : biomeDefinition.entrySet()) {
                var type = VanillaBiomeId.values()[i];
                NbtMap value = (NbtMap) biome.getValue();
                BiomeData data = NbtUtils.createRecordFromNBT(BiomeData.class, value);
                super.register(type, data);
                i++;
            }
        }
    }

    @Override
    public BiomeData register(BiomeType biomeType, BiomeData biomeData) {
        biomeDefinition.putCompound(biomeType.getIdentifier().path(), NbtMap.fromRecord(biomeData));
        return super.register(biomeType, biomeData);
    }

    @Override
    public NbtMap getBiomeDefinition() {
//        return vanillaBiomeDefinition;
        return biomeDefinition.build();
    }
}
