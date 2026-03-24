package org.allaymc.api.item.component;

import org.allaymc.api.utils.DyeColor;

/**
 * Component for items that can act as a dye with a fixed dye color.
 *
 * @author daoge_cmd
 */
public interface ItemDyeComponent extends ItemComponent {
    /**
     * Retrieves the {@link DyeColor} associated with the item component.
     *
     * @return the dye color associated with this component, never {@code null}.
     */
    DyeColor getDyeColor();
}
