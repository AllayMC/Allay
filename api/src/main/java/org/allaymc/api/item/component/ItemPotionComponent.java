package org.allaymc.api.item.component;

import org.allaymc.api.item.data.PotionType;

/**
 * Represents an item that can hold potion effects.
 *
 * @author daoge_cmd
 */
public interface ItemPotionComponent extends ItemComponent {
    /**
     * Gets the potion type of this potion item.
     *
     * @return the potion type of this potion item.
     */
    PotionType getPotionType();

    /**
     * Sets the potion type of this potion item.
     *
     * @param potionType the potion type to set.
     */
    void setPotionType(PotionType potionType);
}
