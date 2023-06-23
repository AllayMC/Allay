package cn.allay.api.world.palette;

/**
 * @author JukeboxMC | daoge_cmd <br>
 * @date 2023/4/14 <br>
 * Allay Project <br>
 */
public interface RuntimeDataDeserializer<V> {
    V deserialize(int id);
}
