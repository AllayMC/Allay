package org.allaymc.api.item.component;

import org.allaymc.api.item.data.ArmorType;

/**
 * {@code ItemWearableComponent} identifies the armor equipment position associated
 * with an item. Equipment interactions use that armor type to select the player's
 * armor-container slot; this capability describes the destination without itself
 * specifying an inventory transfer operation.
 *
 * @author daoge_cmd
 */
public interface ItemWearableComponent extends ItemComponent {
    /**
     * Returns the armor equipment type configured for this item.
     */
    ArmorType getArmorType();
}
