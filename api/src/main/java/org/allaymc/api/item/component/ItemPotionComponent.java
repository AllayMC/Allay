package org.allaymc.api.item.component;

import org.allaymc.api.item.data.PotionType;

/**
 * Represents a potion item.
 *
 * @author daoge_cmd
 */
public interface ItemPotionComponent extends ItemEdibleComponent {
    /**
     * Gets the potion type of this potion item.
     *
     * @return the potion type of this potion item.
     */
    PotionType getPotionType();
}
