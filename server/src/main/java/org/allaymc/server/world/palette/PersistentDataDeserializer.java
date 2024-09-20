package org.allaymc.server.world.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface PersistentDataDeserializer<V> {
    V deserialize(NbtMap nbtMap);
}
