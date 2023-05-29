package cn.allay.server.utils.palette;

/**
 * Author: JukeboxMC | daoge_cmd <br>
 * Date: 2023/4/14 <br>
 * Allay Project <br>
 */
public interface RuntimeDataDeserializer<V> {
    V deserialize(int id);
}
