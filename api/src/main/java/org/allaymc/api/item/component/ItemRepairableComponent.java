package org.allaymc.api.item.component;

import org.allaymc.api.item.type.ItemType;

/**
 * @author IWareQ
 */
public interface ItemRepairableComponent extends ItemComponent {
    /**
     * Determines if this item can be repaired using the specified item type.
     *
     * @param itemType the {@link ItemType} to check for repair compatibility
     *
     * @return {@code true} if the item can be repaired by the given {@link ItemType}, {@code false} otherwise
     */
    boolean canBeRepairedBy(ItemType<?> itemType);
}
