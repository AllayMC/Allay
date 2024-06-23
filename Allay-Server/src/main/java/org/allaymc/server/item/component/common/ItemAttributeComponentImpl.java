package org.allaymc.server.item.component.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.item.component.common.ItemAttributeComponent;
import org.allaymc.api.item.component.common.ItemAttributes;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class ItemAttributeComponentImpl implements ItemAttributeComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_attribute_component");

    protected static final ItemAttributeComponentImpl DEFAULT = new ItemAttributeComponentImpl(ItemAttributes.DEFAULT);

    @Getter
    protected ItemAttributes itemAttributes;

    public static ItemAttributeComponentImpl ofDefault() {
        return DEFAULT;
    }
}
