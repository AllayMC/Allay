package org.allaymc.api.item.component;

import org.allaymc.api.item.data.PotionType;

/**
 * Component for items whose behavior is determined by a potion type.
 *
 * @author daoge_cmd
 */
public interface ItemPotionComponent extends ItemComponent {
    /**
     * Gets the potion type of this potion item.
     *
     * @return the potion type of this potion item
     */
    PotionType getPotionType();

    /**
     * Sets the potion type of this potion item.
     *
     * @param potionType the potion type to set
     */
    void setPotionType(PotionType potionType);
}
