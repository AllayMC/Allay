package org.allaymc.server.registry.loader;

import com.google.gson.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.LiquidReactionOnTouch;
import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;
import org.joml.primitives.AABBd;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockStateDataRegistryLoader implements RegistryLoader<Void, Map<BlockId, Map<Integer, BlockStateData>>> {
    protected static final Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(VoxelShape.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var array = json.getAsJsonArray();
                if (array.isEmpty() || array.get(0).isJsonArray()) {
                    // collisionShape field is a list of AABBs, and can be an empty array
                    var builder = VoxelShape.builder();
                    array.forEach(e -> {
                        var a = e.getAsJsonArray();
                        var minX = a.get(0).getAsDouble();
                        var minY = a.get(1).getAsDouble();
                        var minZ = a.get(2).getAsDouble();
                        var maxX = a.get(3).getAsDouble();
                        var maxY = a.get(4).getAsDouble();
                        var maxZ = a.get(5).getAsDouble();

                        builder.solid(minX, minY, minZ, maxX, maxY, maxZ);
                    });
                    return builder.build();
                } else {
                    var minX = array.get(0).getAsDouble();
                    var minY = array.get(1).getAsDouble();
                    var minZ = array.get(2).getAsDouble();
                    var maxX = array.get(3).getAsDouble();
                    var maxY = array.get(4).getAsDouble();
                    var maxZ = array.get(5).getAsDouble();

                    if (minX == 0 && minY == 0 && minZ == 0 && maxX == 0 && maxY == 0 && maxZ == 0) {
                        return VoxelShape.EMPTY;
                    }

                    return VoxelShape.builder().solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ)).build();
                }
            })
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                // Example: #4c4c4cff
                var str = json.getAsString();
                var color = Color.decode(str.substring(0, 7));
                return new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        Integer.parseInt(str.substring(7), 16));
            })
            .registerTypeAdapter(LiquidReactionOnTouch.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> LiquidReactionOnTouch.valueOf(json.getAsString()))
            .registerTypeAdapter(TintMethod.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var str = json.getAsString();
                return switch (str) {
                    case "None" -> TintMethod.NONE;
                    case "DefaultFoliage" -> TintMethod.DEFAULT_FOLIAGE;
                    case "BirchFoliage" -> TintMethod.BIRCH_FOLIAGE;
                    case "EvergreenFoliage" -> TintMethod.EVERGREEN_FOLIAGE;
                    case "DryFoliage" -> TintMethod.DRY_FOLIAGE;
                    case "Grass" -> TintMethod.GRASS;
                    case "Water" -> TintMethod.WATER;
                    case "Stem" -> TintMethod.STEM;
                    case "RedStoneWire" -> TintMethod.RED_STONE_WIRE;
                    default -> {
                        log.warn("Unknown tint method: {}", str);
                        yield TintMethod.NONE;
                    }
                };
            })
            .create();

    public static BlockStateData fromJson(String json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    public static BlockStateData fromJson(JsonObject json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    @Override
    @SneakyThrows
    public Map<BlockId, Map<Integer, BlockStateData>> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_states.json")))) {
            var loaded = new EnumMap<BlockId, Map<Integer, BlockStateData>>(BlockId.class);
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var obj = entry.getAsJsonObject();
                var type = BlockId.fromIdentifier(new Identifier(obj.get("name").getAsString()));
                if (type == null) {
                    log.warn("Unknown block id: {}", obj.get("name").getAsString());
                    return;
                }

                var blockStateData = fromJson(obj);
                if (!loaded.containsKey(type)) {
                    loaded.put(type, new Int2ObjectOpenHashMap<>());
                }

                loaded.get(type).put(obj.get("blockStateHash").getAsInt(), blockStateData);
            });
            return loaded;
        }
    }
}
