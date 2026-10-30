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
 * {@code BlockStateDataRegistryLoader} reads bundled block-state metadata and indexes it by
 * built-in block ID and state hash. Its serializer reconstructs voxel shapes, RGBA colors,
 * liquid reactions, and tint selections from the data resource rather than relying on ordinary
 * field deserialization for those representations.
 *
 * <p>
 * Voxel data uses either one six-coordinate box or a list of boxes; an empty list and an
 * all-zero single box represent an empty shape. The serializer preserves the alpha suffix
 * when converting hexadecimal colors. Unknown tint labels select {@code TintMethod.NONE}
 * with a warning, while state entries whose block identifier has no built-in ID are skipped
 * so known states remain available.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockStateDataRegistryLoader implements RegistryLoader<Void, Map<BlockId, Map<Integer, BlockStateData>>> {
    /**
     * {@code SERIALIZER} decodes the resource's shape, color, liquid, and tint representations.
     * Unknown tint strings become {@code TintMethod.NONE}; malformed coordinate or color data
     * still fails conversion.
     */
    protected static final Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(VoxelShape.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var array = json.getAsJsonArray();
                if (array.isEmpty() || array.get(0).isJsonArray()) {
                    // The resource encodes multi-box shapes as nested arrays. An empty array
                    // denotes no collision boxes and must remain an empty shape after decoding.
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

                    // A zero-sized single box is the resource's empty-shape sentinel. Preserve
                    // that meaning rather than adding a degenerate solid box to the shape.
                    if (minX == 0 && minY == 0 && minZ == 0 && maxX == 0 && maxY == 0 && maxZ == 0) {
                        return VoxelShape.EMPTY;
                    }

                    return VoxelShape.builder().solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ)).build();
                }
            })
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                // Resource colors use #RRGGBBAA. Decode RGB separately and retain the final
                // alpha byte because Color.decode alone accepts the RGB portion of this format.
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
                        // Unknown resource tint labels have no matching runtime calculation.
                        // Keep the state usable without tinting and report the unsupported label.
                        log.warn("Unknown tint method: {}", str);
                        yield TintMethod.NONE;
                    }
                };
            })
            .create();

    /**
     * Decodes one state with the resource-specific serializer. A {@code null} input produces
     * {@code null}; malformed resource representations still propagate conversion failures, while
     * unknown tint labels use the serializer's no-tint fallback.
     */
    public static BlockStateData fromJson(String json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    /**
     * Decodes one state with the resource-specific serializer. A {@code null} input produces
     * {@code null}; malformed resource representations still propagate conversion failures, while
     * unknown tint labels use the serializer's no-tint fallback.
     */
    public static BlockStateData fromJson(JsonObject json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    /**
     * Loads {@code block_states.json} into mutable maps by block identity and state hash.
     * Unrecognized block identities are warned about and skipped; malformed known entries still
     * abort the load instead of returning the accumulated map.
     */
    @Override
    @SneakyThrows
    public Map<BlockId, Map<Integer, BlockStateData>> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_states.json")))) {
            var loaded = new EnumMap<BlockId, Map<Integer, BlockStateData>>(BlockId.class);
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var obj = entry.getAsJsonObject();
                var type = BlockId.fromIdentifier(new Identifier(obj.get("name").getAsString()));
                if (type == null) {
                    // The resource can contain blocks absent from this runtime's ID table.
                    // Skip those entries so they cannot be indexed under a missing block identity.
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
