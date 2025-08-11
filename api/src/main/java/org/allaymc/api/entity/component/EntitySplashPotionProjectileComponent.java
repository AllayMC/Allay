package org.allaymc.api.entity.component;

import org.allaymc.api.item.data.PotionType;

/**
 * @author daoge_cmd
 */
public interface EntitySplashPotionProjectileComponent extends EntityProjectileComponent {

    /**
     * Gets the potion type that this entity holds.
     *
     * @return the potion type that this entity holds.
     */
    PotionType getPotionType();

    /**
     * Sets the potion type of this entity.
     *
     * @param potionType the potion type to set.
     */
    void setPotionType(PotionType potionType);
}
