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
public interface ItemElement6 extends ItemStack {
    ItemType<ItemElement6> TYPE = ItemTypeBuilder
            .builder(ItemElement6.class)
            .vanillaItem(VanillaItemId.ELEMENT_6, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
