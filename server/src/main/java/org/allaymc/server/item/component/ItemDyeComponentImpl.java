package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.utils.DyeColor;

/**
 * {@code ItemDyeComponentImpl} supplies the fixed dye color configured for an
 * item's dye capability. The constructor retains the color without deriving it
 * from stack metadata, and the generated getter exposes that value to dye-driven
 * interactions.
 *
 * @author daoge_cmd
 */
public class ItemDyeComponentImpl implements ItemDyeComponent {

    /**
     * Configured fixed item dye color.
     */
    @Getter
    protected final DyeColor dyeColor;

    /**
     * Creates a dye capability retaining the supplied fixed color value.
     */
    public ItemDyeComponentImpl(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }
}
