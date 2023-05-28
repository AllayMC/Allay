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
public interface ItemLeatherLeggings extends ItemStack {
    ItemType<ItemLeatherLeggings> TYPE = ItemTypeBuilder
            .builder(ItemLeatherLeggings.class)
            .vanillaItem(VanillaItemId.LEATHER_LEGGINGS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
