package org.allaymc.server.datastruct.palette;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface RuntimeDataDeserializer<V> {
    V deserialize(int id);
}
