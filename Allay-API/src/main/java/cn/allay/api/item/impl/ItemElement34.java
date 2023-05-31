package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement34 extends ItemStack {
    ItemType<ItemElement34> TYPE = ItemTypeBuilder
            .builder(ItemElement34.class)
            .vanillaItem(VanillaItemId.ELEMENT_34, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
