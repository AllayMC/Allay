package org.allaymc.server.datastruct.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface PersistentDataSerializer<V> {
    NbtMap serialize(V value);
}
