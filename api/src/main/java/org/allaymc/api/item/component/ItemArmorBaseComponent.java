package org.allaymc.api.item.component;

import org.allaymc.api.item.data.ArmorType;

/**
 * @author daoge_cmd
 */
public interface ItemArmorBaseComponent extends ItemBaseComponent {

    /**
     * Returns the armor type of the item.
     *
     * @return The armor type
     */
    ArmorType getArmorType();
}
