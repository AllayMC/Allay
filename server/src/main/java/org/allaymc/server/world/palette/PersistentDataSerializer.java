package org.allaymc.server.world.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface PersistentDataSerializer<V> {
    NbtMap serialize(V value);
}
