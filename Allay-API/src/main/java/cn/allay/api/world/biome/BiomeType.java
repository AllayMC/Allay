package cn.allay.api.world.biome;

import cn.allay.api.data.VanillaBiomeId;
import lombok.ToString;

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

    //TODO: Custom biome

    String getName();

    int getId();

    @ToString
    final class SimpleBiomeType implements BiomeType {
        private final String name;
        private final int id;

        public SimpleBiomeType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getId() {
            return id;
        }

        public String name() {
            return name;
        }

        public int id() {
            return id;
        }
    }
}
