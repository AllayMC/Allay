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
public interface ItemElement59 extends ItemStack {
    ItemType<ItemElement59> TYPE = ItemTypeBuilder
            .builder(ItemElement59.class)
            .vanillaItem(VanillaItemId.ELEMENT_59, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
