package cn.allay.api.world.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/4/14
 *
 * @author JukeboxMC | daoge_cmd
 */
public interface PersistentDataDeserializer<V> {
    V deserialize(NbtMap nbtMap);
}
