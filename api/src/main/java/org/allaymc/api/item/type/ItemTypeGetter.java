package org.allaymc.api.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code ItemTypeGetter} resolves item types through the NBT compatibility loader.
 * Callers describe a name or legacy ID with optional metadata, allowing item-state
 * updaters to translate old representations before the current item registry
 * performs lookup. Resolution follows the installed {@code NBTIO} implementation.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class ItemTypeGetter {

    /**
     * Creates a lookup description using the supplied item name.
     * Blank names are rejected immediately; the NBT loader handles identifier
     * interpretation and metadata-dependent compatibility when {@code itemType} is
     * called.
     *
     * @throws IllegalArgumentException if {@code name} is blank
     */
    public static Getter name(String name) {
        return new Getter(name);
    }

    /**
     * Creates a lookup description using a legacy numeric item ID.
     * The ID is stored as text under {@code "Id"}; the NBT compatibility loader
     * interprets it when {@code itemType} is called.
     */
    public static Getter id(int id) {
        return new Getter(id);
    }

    /**
     * {@code Getter} accumulates one NBT item description for compatibility-based type lookup.
     */
    public static final class Getter {
        /**
         * Mutable NBT lookup description.
         */
        private final NbtMapBuilder nbtMapBuilder = NbtMap.builder();

        /**
         * Creates a name-based NBT lookup after rejecting blank input.
         * The supplied name is retained without trimming; the item-state updater and
         * registry interpret it during later resolution.
         *
         * @throws IllegalArgumentException if {@code name} is blank
         */
        private Getter(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            nbtMapBuilder.putString("Name", name);
        }

        /**
         * Creates an NBT lookup storing the legacy numeric ID as text.
         */
        private Getter(int id) {
            nbtMapBuilder.putString("Id", String.valueOf(id));
        }

        /**
         * Selects metadata for this lookup and returns the same description.
         * The value replaces any earlier {@code "Damage"} entry and participates in
         * legacy item-state conversion when the loader resolves the type.
         */
        public Getter meta(int meta) {
            nbtMapBuilder.putInt("Damage", meta);
            return this;
        }

        /**
         * Resolves this description through the installed NBT loader and returns its
         * stack's item type. The server loader converts compatibility data before lookup
         * and returns the air type when loading fails.
         */
        public ItemType<?> itemType() {
            return NBTIO.getAPI().fromItemStackNBT(nbtMapBuilder.build()).getItemType();
        }
    }
}
