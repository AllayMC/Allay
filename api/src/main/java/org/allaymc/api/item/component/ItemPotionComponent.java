package org.allaymc.api.item.component;

import org.allaymc.api.item.data.PotionType;

/**
 * {@code ItemPotionComponent} exposes the potion type selected for an item
 * stack. The server implementation encodes that selection in stack metadata,
 * and consumption or projectile behavior reads the selected type to obtain its
 * effects and color.
 *
 * @author daoge_cmd
 */
public interface ItemPotionComponent extends ItemComponent {
    /**
     * Returns the potion type selected by this item's metadata. The server
     * uses the metadata as an ordinal lookup, so metadata outside the
     * available potion range causes an index failure.
     */
    PotionType getPotionType();

    /**
     * Selects the potion type used by this item's behavior. {@code potionType}
     * must be non-{@code null}; the server stores its ordinal in metadata for
     * subsequent lookups and serialization.
     */
    void setPotionType(PotionType potionType);
}
