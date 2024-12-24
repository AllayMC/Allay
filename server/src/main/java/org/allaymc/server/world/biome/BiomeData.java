package org.allaymc.server.world.biome;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.EnumMap;
import java.util.List;

/**
 * BiomeData represents the data of a biome.
 */
@MinecraftVersionSensitive
public record BiomeData(
        float ash,
        float blue_spores,
        float depth,
        float downfall,
        float height,
        boolean rain,
        float red_spores,
        List<String> tags,
        float temperature,
        float waterColorA,
        float waterColorB,
        float waterColorG,
        float waterColorR,
        float white_ash
) {
    private static final EnumMap<BiomeId, BiomeData> BIOME_DATA = new EnumMap(BiomeId.class);

    static {
        try (var stream = Utils.getResource("biome_definitions.nbt")) {
            var tag = (NbtMap) NbtUtils.createNetworkReader(stream).readTag();
            tag.forEach((key, value) -> {
                BIOME_DATA.put((BiomeId) BiomeId.fromIdentifier(new Identifier("minecraft", key)), fromNBT((NbtMap) value));
            });
        } catch (Exception e) {
            throw new AssertionError("Failed to load biome_definitions.nbt", e);
        }
    }

    public boolean isHumid() {
        return downfall >= 0.85;
    }

    public static BiomeData getBiomeData(BiomeId biomeId) {
        return BIOME_DATA.get(biomeId);
    }

    public static BiomeData getBiomeData(BiomeType biomeType) {
        return getBiomeData((BiomeId) biomeType);
    }

    private static BiomeData fromNBT(NbtMap nbt) {
        return new BiomeData(
                nbt.getFloat("ash"),
                nbt.getFloat("blue_spores"),
                nbt.getFloat("depth"),
                nbt.getFloat("downfall"),
                nbt.getFloat("height"),
                nbt.getBoolean("rain"),
                nbt.getFloat("red_spores"),
                nbt.getList("tags", NbtType.STRING),
                nbt.getFloat("temperature"),
                nbt.getFloat("waterColorA"),
                nbt.getFloat("waterColorB"),
                nbt.getFloat("waterColorG"),
                nbt.getFloat("waterColorR"),
                nbt.getFloat("white_ash")
        );
    }
}
