package org.allaymc.server.registry.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParser;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.server.world.biome.BiomeId;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class BiomeDataRegistryLoader implements RegistryLoader<Void, Map<BiomeId, BiomeData>> {
    protected static final Gson SERIALIZER = new GsonBuilder()
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

    @Override
    public Map<BiomeId, BiomeData> load(Void $) {
        var map = new EnumMap<BiomeId, BiomeData>(BiomeId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("biome_definitions.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().asMap().forEach((id, obj) -> {
                var biomeId = BiomeId.fromIdentifier(new Identifier(id));
                var biomeData = SERIALIZER.fromJson(obj.toString(), BiomeData.class);
                map.put(biomeId, biomeData);
            });
        } catch (Exception e) {
            throw new AssertionError("Failed to load biome_definitions.json", e);
        }
        return map;
    }
}
