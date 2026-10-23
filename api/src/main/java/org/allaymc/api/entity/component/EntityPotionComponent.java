package org.allaymc.api.entity.component;

import org.allaymc.api.item.data.PotionType;

/**
 * {@code EntityPotionComponent} exposes optional potion contents for an
 * entity, including arrows and thrown potions. Consumers use the selected
 * potion to derive appearance or impact effects, and {@code null} represents
 * the absence of configured potion contents.
 *
 * @author daoge_cmd
 */
public interface EntityPotionComponent extends EntityComponent {

    /**
     * Returns the configured potion type, or {@code null} when no contents are
     * assigned. Impact consumers distinguish this absence from a concrete
     * potion type with its own effects.
     */
    PotionType getPotionType();

    /**
     * Changes the configured potion contents and publishes the entity state.
     * Passing {@code null} clears the previous contents, so later effect
     * handling sees an entity without a configured potion type.
     */
    void setPotionType(PotionType potionType);
}
