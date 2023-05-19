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
public interface ItemElement69 extends ItemStack {
    ItemType<ItemElement69> TYPE = ItemTypeBuilder
            .builder(ItemElement69.class)
            .vanillaItem(VanillaItemId.ELEMENT_69, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
