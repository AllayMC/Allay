package org.allaymc.api.item.data;

/**
 * Represents the lock mode of an item.
 *
 * @author daoge_cmd
 */
public enum ItemLockMode {
    /**
     * The item is not locked.
     */
    NONE,
    /**
     * The item is locked in a slot.
     */
    LOCK_IN_SLOT,
    /**
     * The item is locked in the inventory.
     */
    LOCK_IN_INVENTORY
}
