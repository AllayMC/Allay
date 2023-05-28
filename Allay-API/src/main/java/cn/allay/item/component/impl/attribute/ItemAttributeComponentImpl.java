package cn.allay.item.component.impl.attribute;

import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import cn.allay.item.component.ItemComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public class ItemAttributeComponentImpl implements ItemAttributeComponent, ItemComponentImpl {

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

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
