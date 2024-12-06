package org.allaymc.server.datastruct.palette;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface RuntimeDataSerializer<V> {
    int serialize(V value);
}
