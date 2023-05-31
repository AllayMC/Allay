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
public interface ItemSalmon extends ItemStack {
    ItemType<ItemSalmon> TYPE = ItemTypeBuilder
            .builder(ItemSalmon.class)
            .vanillaItem(VanillaItemId.SALMON, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
