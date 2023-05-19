package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement100 extends ItemStack {
    ItemType<ItemElement100> TYPE = ItemTypeBuilder
            .builder(ItemElement100.class)
            .vanillaItem(VanillaItemId.ELEMENT_100, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
