package cn.allay.api.world.palette;

/**
 * Author: JukeboxMC | daoge_cmd <br>
 * Date: 2023/4/14 <br>
 * Allay Project <br>
 */
public interface RuntimeDataSerializer<V> {
    int serialize(V value);
}
