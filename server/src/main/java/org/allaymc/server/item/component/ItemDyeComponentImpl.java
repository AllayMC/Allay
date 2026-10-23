package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.utils.identifier.Identifier;

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
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_dye_component");

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
