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
public interface ItemElement45 extends ItemStack {
    ItemType<ItemElement45> TYPE = ItemTypeBuilder
            .builder(ItemElement45.class)
            .vanillaItem(VanillaItemId.ELEMENT_45, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
