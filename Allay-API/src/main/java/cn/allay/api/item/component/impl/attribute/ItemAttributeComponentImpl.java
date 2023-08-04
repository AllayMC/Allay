package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.component.ItemComponentImpl;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class ItemAttributeComponentImpl implements ItemAttributeComponent, ItemComponentImpl {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_attribute_component");

    protected ItemAttributes itemAttributes;

    public ItemAttributeComponentImpl(ItemAttributes itemAttributes) {
        this.itemAttributes = itemAttributes;
    }

    @Override
    @Impl
    public ItemAttributes getItemAttributes() {
        return itemAttributes;
    }
}
