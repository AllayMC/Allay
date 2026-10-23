package org.allaymc.api.item.creative;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * {@code CreativeItemRegistry} exposes creative categories and their indexed
 * groups and item entries. Plugins register additions through categories and
 * groups before the server freezes registration for protocol initialization;
 * clients identify selected entries using the corresponding positive network
 * index.
 *
 * @author daoge_cmd
 */
public interface CreativeItemRegistry {
    /**
     * Returns the creative registry's {@code CONSTRUCTION} inventory category instance.
     */
    default CreativeItemCategory getConstructionCategory() {
        return getCategory(CreativeItemCategory.Type.CONSTRUCTION);
    }

    /**
     * Returns the creative registry's {@code NATURE} inventory category instance.
     */
    default CreativeItemCategory getNatureCategory() {
        return getCategory(CreativeItemCategory.Type.NATURE);
    }

    /**
     * Returns the creative registry's {@code EQUIPMENT} inventory category instance.
     */
    default CreativeItemCategory getEquipmentCategory() {
        return getCategory(CreativeItemCategory.Type.EQUIPMENT);
    }

    /**
     * Returns the creative registry's {@code ITEMS} inventory category instance.
     */
    default CreativeItemCategory getItemsCategory() {
        return getCategory(CreativeItemCategory.Type.ITEMS);
    }

    /**
     * Returns the creative inventory category for the supplied tab type.
     */
    CreativeItemCategory getCategory(CreativeItemCategory.Type type);

    /**
     * Returns the creative entry at the supplied zero-based registry index.
     * The index must identify a registered entry; the list-backed implementation
     * throws when it falls outside the registered range.
     *
     * @throws IndexOutOfBoundsException if {@code index} is outside the registered range
     */
    CreativeItemEntry getEntryByIndex(int index);

    /**
     * Returns the creative group at the supplied zero-based registry index.
     * The index must identify a registered group; the list-backed implementation
     * throws when it falls outside the registered range.
     *
     * @throws IndexOutOfBoundsException if {@code index} is outside the registered range
     */
    CreativeItemGroup getGroupByIndex(int index);

    /**
     * Returns an unmodifiable view of all registered creative entries.
     * List positions match registry indices and reflect later registrations; the
     * contained objects remain shared with their category or group owners.
     */
    @UnmodifiableView
    List<CreativeItemEntry> getEntries();

    /**
     * Returns an unmodifiable view of all registered creative groups.
     * List positions match registry indices and reflect later registrations; the
     * contained objects remain shared with their category or group owners.
     */
    @UnmodifiableView
    List<CreativeItemGroup> getGroups();
}
