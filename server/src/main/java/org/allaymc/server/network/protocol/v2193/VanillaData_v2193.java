package org.allaymc.server.network.protocol.v2193;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.SerializableVoxelShape;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Vanilla block data that 1.26.50 clients need from the server before they can join.
 * <p>
 * The data-driven block definitions and the voxel shapes are BDS 1.26.50 dumps published in CloudburstMC/Data
 * (Apache-2.0).
 */
@UtilityClass
final class VanillaData_v2193 {

    /**
     * Definitions of the vanilla blocks that 1.26.50 implements as data-driven blocks, such as the wool and concrete
     * stairs and slabs. The client builds these blocks from the definitions sent in {@code StartGamePacket}.
     */
    static final List<BlockPropertyData> DATA_DRIVEN_BLOCK_PROPERTIES = read("data_driven_blocks.nbt").entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .map(entry -> new BlockPropertyData(entry.getKey(), (NbtMap) entry.getValue()))
            .toList();

    /**
     * The vanilla voxel shapes BDS registers, in network order. Data-driven blocks such as the wool stairs resolve their
     * shapes through this registry, so an empty one makes the client reject the session.
     */
    static final List<SerializableVoxelShape> VOXEL_SHAPES;

    /**
     * The vanilla voxel shape names mapped to their index in {@link #VOXEL_SHAPES}.
     */
    static final Map<String, Integer> VOXEL_SHAPE_NAMES;

    static {
        try (var reader = new InputStreamReader(Utils.getResource("voxel_shapes.json"))) {
            var root = JsonParser.parseReader(reader).getAsJsonObject();
            var names = new LinkedHashMap<String, Integer>();
            root.getAsJsonObject("names").entrySet().forEach(entry -> names.put(entry.getKey(), entry.getValue().getAsInt()));
            VOXEL_SHAPE_NAMES = Collections.unmodifiableMap(names);
            VOXEL_SHAPES = root.getAsJsonArray("shapes").asList().stream()
                    .map(shape -> readShape(shape.getAsJsonObject()))
                    .toList();
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to load voxel_shapes.json", exception);
        }
    }

    private static SerializableVoxelShape readShape(JsonObject shape) {
        var cells = shape.getAsJsonObject("cells");
        return new SerializableVoxelShape(
                new SerializableVoxelShape.SerializableCells(
                        cells.get("xSize").getAsShort(),
                        cells.get("ySize").getAsShort(),
                        cells.get("zSize").getAsShort(),
                        cells.getAsJsonArray("storage").asList().stream().map(JsonElement::getAsShort).toList()
                ),
                floats(shape.getAsJsonArray("xCoordinates")),
                floats(shape.getAsJsonArray("yCoordinates")),
                floats(shape.getAsJsonArray("zCoordinates"))
        );
    }

    private static List<Float> floats(JsonArray array) {
        return array.asList().stream().map(JsonElement::getAsFloat).toList();
    }

    private static NbtMap read(String resource) {
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource(resource)))) {
            return (NbtMap) reader.readTag();
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to load " + resource, exception);
        }
    }
}
