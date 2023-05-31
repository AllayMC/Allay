package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.component.ItemComponentImpl;

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
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
