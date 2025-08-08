package org.allaymc.server.world.biome;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * BiomeData represents the data of a biome.
 */
@MinecraftVersionSensitive
public record BiomeData(
        float ashDensity,
        float blueSporeDensity,
        float depth,
        float downfall,
        @SerializedName("mapWaterColour")
        Color mapWaterColor,
        boolean rain,
        float redSporeDensity,
        float scale,
        List<String> tags,
        float temperature,
        float whiteAshDensity
) {
    private static final Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var obj = json.getAsJsonObject();
                return new Color(
                        obj.get("r").getAsInt(),
                        obj.get("g").getAsInt(),
                        obj.get("b").getAsInt(),
                        obj.get("a").getAsInt()
                );
            })
            .create();
    private static final Map<BiomeId, BiomeData> BIOME_DATA = new EnumMap<>(BiomeId.class);

    static {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("biome_definitions.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().asMap().forEach((id, obj) -> {
                var biomeId = (BiomeId) BiomeId.fromIdentifier(new Identifier(id));
                var biomeData = SERIALIZER.fromJson(obj.toString(), BiomeData.class);
                BIOME_DATA.put(biomeId, biomeData);
            });
        } catch (Exception e) {
            throw new AssertionError("Failed to load biome_definitions.json", e);
        }
    }

    public static BiomeData getBiomeData(BiomeId biomeId) {
        return BIOME_DATA.get(biomeId);
    }

    public static BiomeData getBiomeData(BiomeType biomeType) {
        return getBiomeData((BiomeId) biomeType);
    }

    public boolean isHumid() {
        return downfall >= 0.85;
    }

    public BiomeDefinitionData toNetworkData() {
        return new BiomeDefinitionData(
                null, temperature, downfall, redSporeDensity,
                blueSporeDensity, ashDensity, whiteAshDensity, depth,
                scale, mapWaterColor, rain, tags, null
        );
    }
}
