package org.allaymc.api.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * ItemTypeSafeGetter is used to get an item type safely, which means that
 * the plugin developers can get an item type without worrying about being broken
 * in the next minecraft version compared to using {@link ItemTypes} directly.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ItemTypeSafeGetter {

    /**
     * Get an item type by its name.
     *
     * @param name The name of the item type.
     *
     * @return The {@link Getter} object.
     */
    public static Getter name(String name) {
        return new Getter(name);
    }

    /**
     * Get an item type by its id.
     *
     * @param id The id of the item type.
     *
     * @return The {@link Getter} object.
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
         * @param meta The meta of the item.
         *
         * @return The {@link Getter} object.
         */
        public Getter meta(int meta) {
            nbtMapBuilder.putInt("Damage", meta);
            return this;
        }

        /**
         * Try to get the item type.
         *
         * @return The item type.
         */
        public ItemType<?> itemType() {
            var updatedNbt = ItemStateUpdaters.updateItemState(nbtMapBuilder.build(), ItemStateUpdaters.LATEST_VERSION);
            var itemType = Registries.ITEMS.get(new Identifier(updatedNbt.getString("Name")));
            if (itemType == null) {
                throw new IllegalArgumentException("Unknown item type " + updatedNbt.getString("Name"));
            }
            return itemType;
        }
    }
}
