package cn.allay.api.item.component.attribute;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class ItemAttributeComponentImpl implements ItemAttributeComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_attribute_component");

    protected ItemAttributes itemAttributes;

    public ItemAttributeComponentImpl(ItemAttributes itemAttributes) {
        this.itemAttributes = itemAttributes;
    }

    @Override
    public ItemAttributes getItemAttributes() {
        return itemAttributes;
    }
}
