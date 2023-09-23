package cn.allay.api.item.interfaces.element40;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement40Stack extends ItemStack {
    ItemType<ItemElement40Stack> ELEMENT_40_TYPE = ItemTypeBuilder
            .builder(ItemElement40Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_40)
            .build();
}
