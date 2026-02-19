package org.allaymc.api.entity.component;

import org.allaymc.api.item.ItemStack;

/**
 * Component for animal entities that can breed and grow.
 *
 * @author daoge_cmd
 */
public interface EntityAnimalComponent extends EntityComponent {

    /**
     * Check if this animal is a baby.
     *
     * @return {@code true} if this animal is a baby
     */
    boolean isBaby();

    /**
     * Set whether this animal is a baby.
     *
     * @param baby {@code true} to make this animal a baby
     */
    void setBaby(boolean baby);

    /**
     * Check if the given item is a breeding item for this animal.
     *
     * @param item the item to check
     * @return {@code true} if this item can be used to breed this animal
     */
    boolean isBreedingItem(ItemStack item);
}
