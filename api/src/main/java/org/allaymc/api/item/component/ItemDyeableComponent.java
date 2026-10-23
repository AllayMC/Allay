package org.allaymc.api.item.component;

import java.awt.*;

/**
 * {@code ItemDyeableComponent} exposes an optional custom color on an item.
 * {@code null} represents the absence of an applied color, and the shared query
 * and clearing operations use that representation so callers need no separate
 * presence flag.
 *
 * @author daoge_cmd
 */
public interface ItemDyeableComponent extends ItemComponent {

    /**
     * Returns the currently applied custom color for this item.
     * The result is {@code null} when the item has no custom color, which
     * {@code hasCustomColor} exposes as an absence check.
     */
    Color getCustomColor();

    /**
     * Sets the currently applied custom color for this item.
     * Supplying {@code null} clears the applied color and makes {@code hasCustomColor}
     * return {@code false} until another color is set.
     */
    void setCustomColor(Color color);

    /**
     * Returns whether this item currently has a non-{@code null} custom color.
     */
    default boolean hasCustomColor() {
        return getCustomColor() != null;
    }

    /**
     * Clears this item's custom color by assigning {@code null}.
     */
    default void clearCustomColor() {
        setCustomColor(null);
    }
}
