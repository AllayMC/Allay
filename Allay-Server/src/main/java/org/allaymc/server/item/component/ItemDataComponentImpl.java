package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.item.ItemData;
import org.allaymc.api.item.component.ItemDataComponent;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class ItemDataComponentImpl implements ItemDataComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_data_component");

    protected static final ItemDataComponentImpl DEFAULT = new ItemDataComponentImpl(ItemData.DEFAULT);

    @Getter
    protected ItemData itemData;

    public static ItemDataComponentImpl ofDefault() {
        return DEFAULT;
    }
}
