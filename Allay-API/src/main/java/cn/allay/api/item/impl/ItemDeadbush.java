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
public interface ItemDeadbush extends ItemStack {
    ItemType<ItemDeadbush> TYPE = ItemTypeBuilder
            .builder(ItemDeadbush.class)
            .vanillaItem(VanillaItemId.DEADBUSH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
