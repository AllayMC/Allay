package org.allaymc.api.item.data;

/**
 * {@code ItemLockMode} represents the inventory lock policy stored on an item.
 * Item extra-tag persistence encodes the policy as its enum ordinal under
 * {@code "minecraft:item_lock"}; {@code NONE} is the default and is omitted when
 * the base component saves an item.
 *
 * @author daoge_cmd
 */
public enum ItemLockMode {
    /**
     * No inventory lock policy.
     */
    NONE,
    /**
     * Lock to current slot.
     */
    LOCK_IN_SLOT,
    /**
     * Lock within current inventory.
     */
    LOCK_IN_INVENTORY
}
