package org.allaymc.server.datastruct.palette;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface IntDeserializer<V> {
    V deserialize(int id);
}
