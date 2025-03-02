package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Represents a single creative item group, which can contain multiple items.
 * It is collapsed by default, but can be expanded by clicking.
 *
 * @author daoge_cmd
 */
public interface CreativeItemGroup {
    /**
     * Register an item to this group.
     *
     * @param itemStack the item to register.
     *
     * @return the assigned index of this item stack, can then be used to get the registered item stack
     * by using method {@link CreativeItemRegistry#getEntryByIndex(int)}. Client will send back the
     * index when the item is selected.
     */
    CreativeItemEntry registerItem(ItemStack itemStack);

    /**
     * Get the items in this group.
     *
     * @return the items in this group.
     */
    @UnmodifiableView
    Map<Integer, CreativeItemEntry> getItems();

    /**
     * Get the name of this group.
     *
     * @return the name of this group.
     */
    String getName();

    /**
     * Get the icon of this group. The icon will be shown if the group is collapsed.
     *
     * @return the icon of this group.
     */
    ItemStack getIcon();

    /**
     * Get the index of this group. The index is used to identify this group.
     *
     * @return the index of this group.
     */
    int getIndex();

    /**
     * Get the category of this group.
     *
     * @return the category of this group.
     */
    CreativeItemCategory getCategory();
}