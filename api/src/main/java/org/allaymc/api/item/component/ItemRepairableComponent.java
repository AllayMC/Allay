package org.allaymc.api.item.component;

import org.allaymc.api.item.type.ItemType;

/**
 * Component for items that define which materials can be used to repair them.
 *
 * @author IWareQ
 */
public interface ItemRepairableComponent extends ItemComponent {
    /**
     * Checks if this item can be repaired with a given item type.
     *
     * @param itemType the {@link ItemType} to check
     *
     * @return {@code true} if repairable, {@code false} otherwise
     */
    boolean canBeRepairedBy(ItemType<?> itemType);
}
