package cn.allay.api.world.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author JukeboxMC | daoge_cmd <br>
 * @date 2023/4/14 <br>
 * Allay Project <br>
 */
public interface PersistentDataDeserializer<V> {
    V deserialize(NbtMap nbtMap);
}
