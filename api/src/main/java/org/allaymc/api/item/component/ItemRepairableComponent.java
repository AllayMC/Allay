package org.allaymc.api.item.component;

import org.allaymc.api.item.type.ItemType;

/**
 * {@code ItemRepairableComponent} identifies item types accepted as repair
 * materials for an item. Repairing callers query this capability with a candidate
 * material type; the component answers material compatibility without performing
 * the repair or consuming either item stack.
 *
 * @author IWareQ
 */
public interface ItemRepairableComponent extends ItemComponent {
    /**
     * Returns whether the candidate item type is an accepted repair material.
     */
    boolean canBeRepairedBy(ItemType<?> itemType);
}
