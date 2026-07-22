package org.allaymc.server.block.type;

import org.cloudburstmc.nbt.NbtMap;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Protocol-independent client definition for a custom block type.
 * <p>
 * State definitions are resolved while the block type is built. Protocol implementations later combine them with
 * the corresponding {@code BlockStateData} and encode the result for their target Bedrock version.
 *
 * @param rotationOffset   placement rotation offset in degrees
 * @param stateDefinitions immutable definitions keyed by block state hash
 * @param rawComponents    raw global Bedrock components used as an explicit extension point
 * @author daoge_cmd
 */
public record CustomBlockDefinition(
        float rotationOffset,
        Map<Integer, CustomBlockStateDefinition> stateDefinitions,
        Map<String, NbtMap> rawComponents
) {
    public CustomBlockDefinition {
        if (rotationOffset != 0f && rotationOffset != 90f && rotationOffset != 180f && rotationOffset != 270f) {
            throw new IllegalArgumentException("Rotation offset must be 0, 90, 180, or 270 degrees");
        }
        stateDefinitions = immutableCopy(stateDefinitions, "stateDefinitions");
        rawComponents = rawComponents == null
                ? Map.of()
                : immutableCopy(rawComponents, "rawComponents");
    }

    private static <K, V> Map<K, V> immutableCopy(Map<K, V> values, String name) {
        Objects.requireNonNull(values, name);
        var copy = new LinkedHashMap<K, V>();
        values.forEach((key, value) -> copy.put(
                Objects.requireNonNull(key, name + " contains a null key"),
                Objects.requireNonNull(value, name + " contains a null value")
        ));
        return Collections.unmodifiableMap(copy);
    }
}
