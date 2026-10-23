package org.allaymc.server.block.type;

import org.cloudburstmc.nbt.NbtMap;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code CustomBlockDefinition} represents a custom block type's client appearance
 * through state-hash definitions, a placement rotation offset, and raw components.
 * Its constructor copies {@code stateDefinitions} and {@code rawComponents} in
 * insertion order and exposes unmodifiable containers while retaining the map
 * values themselves. Protocol encoders later combine these values with physical
 * block-state metadata.
 *
 * @param rotationOffset placement rotation offset, degrees
 * @param stateDefinitions state-hash definition snapshot; all keys and values must
 * be non-{@code null}, and the supplied container is copied
 * @param rawComponents global component snapshot; {@code null} selects an empty
 * map, and NBT payload references remain shared without recursive copying
 *
 * @author daoge_cmd
 */
public record CustomBlockDefinition(
        float rotationOffset,
        Map<Integer, CustomBlockStateDefinition> stateDefinitions,
        Map<String, NbtMap> rawComponents
) {
    /**
     * Creates the definition after validating a cardinal placement rotation and
     * copying both maps. {@code stateDefinitions} must be non-{@code null}; {@code
     * rawComponents} may be {@code null}, but supplied maps cannot contain {@code null}
     * keys or values.
     *
     * @throws IllegalArgumentException if the rotation is not {@code 0}, {@code
     * 90}, {@code 180}, or {@code 270} degrees
     */
    public CustomBlockDefinition {
        if (rotationOffset != 0f && rotationOffset != 90f && rotationOffset != 180f && rotationOffset != 270f) {
            throw new IllegalArgumentException("Rotation offset must be 0, 90, 180, or 270 degrees");
        }
        stateDefinitions = immutableCopy(stateDefinitions, "stateDefinitions");
        rawComponents = rawComponents == null
                ? Map.of()
                : immutableCopy(rawComponents, "rawComponents");
    }

    /**
     * Returns an unmodifiable insertion-ordered copy of the supplied map. Keys and
     * values must be non-{@code null}; their references remain shared, so the copy
     * protects the container rather than recursively cloning its contents.
     */
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
