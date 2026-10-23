package org.allaymc.api.entity.component;

import org.allaymc.api.item.ItemStack;

/**
 * {@code EntityAnimalComponent} identifies items usable for breeding a
 * particular animal. Feeding logic queries this component before applying
 * breeding behavior, so each animal implementation defines its accepted items
 * independently of the common entity lifecycle and inventory contracts.
 *
 * @author daoge_cmd
 */
public interface EntityAnimalComponent extends EntityComponent {

    /**
     * Returns whether the supplied item is accepted for this animal's
     * breeding.
     */
    boolean isBreedingItem(ItemStack item);
}
