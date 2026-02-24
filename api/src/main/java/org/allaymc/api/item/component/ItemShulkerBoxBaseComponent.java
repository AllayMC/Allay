package org.allaymc.api.item.component;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * ItemShulkerBoxBaseComponent is the base component for shulker box items.
 *
 * @author daoge_cmd
 */
public interface ItemShulkerBoxBaseComponent extends ItemBaseComponent {
    /**
     * Get the stored items.
     * <p>
     * The key is the slot index, and the value is the item stack in that slot.
     *
     * @return The stored items
     */
    @UnmodifiableView
    Map<Integer, ItemStack> getStoredItems();

    /**
     * Set the stored items.
     * <p>
     * The key is the slot index, and the value is the item stack to place in that slot.
     *
     * @param items The stored items
     */
    void setStoredItems(Map<Integer, ItemStack> items);
}
