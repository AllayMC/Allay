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
public interface ItemElement108 extends ItemStack {
    ItemType<ItemElement108> TYPE = ItemTypeBuilder
            .builder(ItemElement108.class)
            .vanillaItem(VanillaItemId.ELEMENT_108, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
