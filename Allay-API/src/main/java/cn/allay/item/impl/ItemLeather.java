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
public interface ItemLeather extends ItemStack {
    ItemType<ItemLeather> TYPE = ItemTypeBuilder
            .builder(ItemLeather.class)
            .vanillaItem(VanillaItemId.LEATHER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
