package org.allaymc.api.item.component;

import org.allaymc.api.item.data.ArmorType;

/**
 * Component for items that can be equipped as wearable armor.
 *
 * @author daoge_cmd
 */
public interface ItemWearableComponent extends ItemComponent {
    /**
     * Returns the armor type of this item.
     *
     * @return The armor type
     */
    ArmorType getArmorType();
}
