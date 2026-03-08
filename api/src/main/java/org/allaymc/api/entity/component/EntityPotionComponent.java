package org.allaymc.api.entity.component;

import org.allaymc.api.item.data.PotionType;

/**
 * Represents an entity that can hold a potion type.
 *
 * @author daoge_cmd
 */
public interface EntityPotionComponent extends EntityComponent {

    /**
     * Gets the potion type that this entity holds.
     *
     * @return the potion type that this entity holds, or {@code null} if no potion type is set
     */
    PotionType getPotionType();

    /**
     * Sets the potion type of this entity.
     *
     * @param potionType the potion type to set, or {@code null} to clean any already set potion type
     */
    void setPotionType(PotionType potionType);
}
