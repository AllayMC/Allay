package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement102 extends ItemStack {
    ItemType<ItemElement102> TYPE = ItemTypeBuilder
            .builder(ItemElement102.class)
            .vanillaItem(VanillaItemId.ELEMENT_102, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
