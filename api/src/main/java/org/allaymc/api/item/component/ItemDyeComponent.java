package org.allaymc.api.item.component;

import org.allaymc.api.utils.DyeColor;

/**
 * {@code ItemDyeComponent} associates a dye item with its dye color. Consumers can
 * resolve the color through this component instead of mapping individual item
 * identifiers, allowing dye-driven interactions to operate on the capability
 * provided by the item type.
 *
 * @author daoge_cmd
 */
public interface ItemDyeComponent extends ItemComponent {
    /**
     * Returns the dye color configured for this item component.
     */
    DyeColor getDyeColor();
}
