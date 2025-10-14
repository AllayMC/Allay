package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class ItemDyeComponentImpl implements ItemDyeComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_dye_component");

    @Getter
    protected final DyeColor dyeColor;

    public ItemDyeComponentImpl(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }
}
