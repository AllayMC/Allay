package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code CreativeItemCategory} contains named and unnamed creative-inventory
 * groups for one inventory tab. Group indices belong to the whole creative
 * registry, while this category restricts index and name lookups to its own
 * registered groups.
 *
 * @author daoge_cmd
 */
public interface CreativeItemCategory {
    /**
     * Returns this category's group at the supplied registry-wide index.
     * The result is {@code null} when the index belongs to no group in this category,
     * even if another category contains that index.
     */
    CreativeItemGroup getGroup(int index);

    /**
     * Returns the group most recently registered under the supplied name.
     * The name must not be blank; an unknown nonblank name returns {@code null}.
     * Matching uses the registered name without trimming or translation.
     *
     * @throws IllegalArgumentException if {@code name} is blank
     */
    CreativeItemGroup getNamedGroup(String name);

    /**
     * Registers a group with the supplied name and icon references.
     * The name and icon must be non-{@code null}; duplicate names replace name lookup
     * while earlier groups remain registered by index. Registration fails after the
     * registry freezes.
     */
    CreativeItemGroup registerGroup(String name, ItemStack icon);

    /**
     * Registers a group with an empty name and an air icon.
     * The group receives its own registry-wide index without entering named lookup,
     * so a category can contain multiple unnamed groups before registration freezes.
     */
    CreativeItemGroup registerUnnamedGroup();

    /**
     * Returns an unmodifiable view of this category's registry-indexed groups.
     * The view reflects additions to the backing map; its group values remain the
     * same registered objects rather than copies.
     */
    @UnmodifiableView
    Map<Integer, CreativeItemGroup> getGroups();

    /**
     * Returns the creative-inventory tab represented by this category instance.
     */
    Type getType();

    /**
     * {@code Type} identifies the four category tabs supported by creative inventory.
     */
    enum Type {
        /**
         * Construction inventory category tab.
         */
        CONSTRUCTION,
        /**
         * Nature inventory category tab.
         */
        NATURE,
        /**
         * Equipment inventory category tab.
         */
        EQUIPMENT,
        /**
         * Items inventory category tab.
         */
        ITEMS,
    }
}
