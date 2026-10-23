package org.allaymc.server.datastruct.palette;

/**
 * {@code IntDeserializer} resolves an integer palette identifier to a runtime value.
 * Palette readers invoke it for each serialized entry after decoding the surrounding wire
 * or storage representation. The integer format itself remains the responsibility of the
 * palette reader.
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface IntDeserializer<V> {
    /**
     * Returns the runtime value identified by the supplied serialized integer.
     */
    V deserialize(int id);
}
