package org.allaymc.api.item.creative;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Represents the registry for creative items. Plugin can modify the creative items through the methods inside.
 *
 * @author daoge_cmd
 */
public interface CreativeItemRegistry {
    /**
     * Get the construction category.
     *
     * @return the construction category
     */
    default CreativeItemCategory getConstructionCategory() {
        return getCategory(CreativeItemCategory.Type.CONSTRUCTION);
    }

    /**
     * Get the decoration category.
     *
     * @return the decoration category
     */
    default CreativeItemCategory getNatureCategory() {
        return getCategory(CreativeItemCategory.Type.NATURE);
    }

    /**
     * Get the equipment category.
     *
     * @return the equipment category
     */
    default CreativeItemCategory getEquipmentCategory() {
        return getCategory(CreativeItemCategory.Type.EQUIPMENT);
    }

    /**
     * Get the items category.
     *
     * @return the items category
     */
    default CreativeItemCategory getItemsCategory() {
        return getCategory(CreativeItemCategory.Type.ITEMS);
    }

    /**
     * Get the specified category.
     *
     * @param type the type of the category
     * @return the specified category
     */
    CreativeItemCategory getCategory(CreativeItemCategory.Type type);

    /**
     * Get the creative item by index.
     *
     * @param index the index of the creative item
     * @return the creative item, or {@code null} if not found
     */
    CreativeItemEntry getEntryByIndex(int index);

    /**
     * Get the creative item group by index.
     *
     * @param index the index of the creative item group
     * @return the creative item group, or {@code null} if not found
     */
    CreativeItemGroup getGroupByIndex(int index);

    /**
     * Get all registered creative items.
     *
     * @return the registered creative items
     */
    @UnmodifiableView
    List<CreativeItemEntry> getEntries();

    /**
     * Get all registered creative item groups.
     *
     * @return the registered creative item groups
     */
    @UnmodifiableView
    List<CreativeItemGroup> getGroups();
}
