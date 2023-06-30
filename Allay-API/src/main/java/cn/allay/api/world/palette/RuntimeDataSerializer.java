package cn.allay.api.world.palette;

/**
 * Allay Project 2023/4/14
 *
 * @author JukeboxMC | daoge_cmd
 */
public interface RuntimeDataSerializer<V> {
    int serialize(V value);
}
