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
public interface ItemBeef extends ItemStack {
    ItemType<ItemBeef> TYPE = ItemTypeBuilder
            .builder(ItemBeef.class)
            .vanillaItem(VanillaItemId.BEEF, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
