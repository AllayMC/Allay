package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.component.data.ItemData;
import org.allaymc.api.item.component.data.ItemDataComponent;
import org.allaymc.server.component.annotation.Identifier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class ItemDataComponentImpl implements ItemDataComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:item_data_component");

    protected static final ItemDataComponentImpl DEFAULT = new ItemDataComponentImpl(ItemData.DEFAULT);

    @Getter
    protected ItemData itemData;

    public static ItemDataComponentImpl ofDefault() {
        return DEFAULT;
    }
}
