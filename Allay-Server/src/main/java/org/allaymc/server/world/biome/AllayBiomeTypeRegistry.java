package org.allaymc.server.world.biome;

import lombok.SneakyThrows;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.registry.MappedRegistry;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.cloudburstmc.nbt.MutableNbtMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayBiomeTypeRegistry extends MappedRegistry<BiomeType, BiomeData, Map<BiomeType, BiomeData>> implements BiomeTypeRegistry {

    private MutableNbtMap biomeDefinition;

    public AllayBiomeTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
        loadVanillaBiomeDefinition();
    }

    @SneakyThrows
    private void loadVanillaBiomeDefinition() {
        try (var stream = Utils.getResource("biome_definitions.nbt")) {
            biomeDefinition = MutableNbtMap.from((NbtMap) NbtUtils.createGZIPReader(stream).readTag());

            int i = 0;
            for (var biome : biomeDefinition.entrySet()) {
                var type = VanillaBiomeId.values()[i];
                var value = (NbtMap) biome.getValue();
                var data = NbtUtils.createRecordFromNBT(BiomeData.class, value);
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
        return biomeDefinition.build();
    }
}
