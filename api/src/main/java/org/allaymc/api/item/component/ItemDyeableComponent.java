package org.allaymc.api.item.component;

import java.awt.Color;

/**
 * Component for items that can be dyed with custom colors.
 * <p>
 * This includes leather armor pieces and wolf armor that can be
 * colored using dyes in a cauldron.
 *
 * @author daoge_cmd
 */
public interface ItemDyeableComponent extends ItemComponent {

    /**
     * Gets the custom color of this item.
     *
     * @return the custom color, or {@code null} if no custom color is set
     */
    Color getCustomColor();

    /**
     * Sets the custom color of this item.
     *
     * @param color the color to set, or {@code null} to clear
     */
    void setCustomColor(Color color);

    /**
     * Checks if this item has a custom color.
     *
     * @return {@code true} if has custom color
     */
    default boolean hasCustomColor() {
        return getCustomColor() != null;
    }

    /**
     * Clears the custom color from this item.
     */
    default void clearCustomColor() {
        setCustomColor(null);
    }
}
