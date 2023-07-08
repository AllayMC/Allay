package cn.allay.api.world.biome;

import cn.allay.api.data.VanillaBiomeId;

/**
 * Allay Project 2023/6/3
 *
 * @author daoge_cmd
 */
public interface BiomeType {
    static BiomeType create(VanillaBiomeId id) {
        var type = new SimpleBiomeType(id.getName(), id.getId());
        BiomeTypeRegistry.getRegistry().register(type.getName(), type);
        return type;
    }

    String getName();

    int getId();
}
