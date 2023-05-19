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
public interface ItemElement99 extends ItemStack {
    ItemType<ItemElement99> TYPE = ItemTypeBuilder
            .builder(ItemElement99.class)
            .vanillaItem(VanillaItemId.ELEMENT_99, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
