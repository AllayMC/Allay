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
public interface ItemElement43 extends ItemStack {
    ItemType<ItemElement43> TYPE = ItemTypeBuilder
            .builder(ItemElement43.class)
            .vanillaItem(VanillaItemId.ELEMENT_43, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
