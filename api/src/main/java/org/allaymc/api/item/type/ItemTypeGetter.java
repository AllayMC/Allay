package org.allaymc.api.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * ItemTypeGetter is used to get an item type safely using {@link NBTIO}, which means
 * that the plugin developers can get an item type without worrying about being broken
 * in the next minecraft version compared to using {@link ItemTypes} directly.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ItemTypeGetter {

    /**
     * Get an item type by its name.
     *
     * @param name The name of the item type
     * @return The {@link Getter} object
     */
    public static Getter name(String name) {
        return new Getter(name);
    }

    /**
     * Get an item type by its id.
     *
     * @param id The id of the item type
     * @return The {@link Getter} object
     */
    public static Getter id(int id) {
        return new Getter(id);
    }

    public static final class Getter {
        private final NbtMapBuilder nbtMapBuilder = NbtMap.builder();

        private Getter(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            nbtMapBuilder.putString("Name", name);
        }

        private Getter(int id) {
            nbtMapBuilder.putString("Id", String.valueOf(id));
        }

        /**
         * Set the meta of the item.
         *
         * @param meta The meta of the item
         * @return The {@link Getter} object
         */
        public Getter meta(int meta) {
            nbtMapBuilder.putInt("Damage", meta);
            return this;
        }

        /**
         * Try to get the item type.
         *
         * @return the item type, or {@link ItemTypes#UNKNOWN} if failed to get the item type
         */
        public ItemType<?> itemType() {
            return NBTIO.getAPI().fromItemStackNBT(nbtMapBuilder.build()).getItemType();
        }
    }
}
