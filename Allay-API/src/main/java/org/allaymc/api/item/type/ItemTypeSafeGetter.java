package org.allaymc.api.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class ItemTypeSafeGetter {

    public static Getter name(String name) {
        return new Getter(name);
    }

    public static Getter id(int id) {
        return new Getter(id);
    }

    public static final class Getter {
        private final NbtMapBuilder nbtMapBuilder = NbtMap.builder();

        public Getter(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            nbtMapBuilder.putString("Name", name);
        }

        public Getter(int id) {
            nbtMapBuilder.putString("Id", String.valueOf(id));
        }

        public Getter meta(int meta) {
            nbtMapBuilder.putInt("Damage", meta);
            return this;
        }

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
