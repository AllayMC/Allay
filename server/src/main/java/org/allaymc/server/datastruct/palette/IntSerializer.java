package org.allaymc.server.datastruct.palette;

/**
 * {@code IntSerializer} maps a runtime palette value to an integer identifier. Palette
 * writers encode that identifier as a network variable-length integer or storage
 * little-endian integer, keeping value resolution separate from the surrounding palette
 * framing.
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface IntSerializer<V> {
    /**
     * Returns the serialized integer identifier for the supplied runtime value.
     */
    int serialize(V value);
}
