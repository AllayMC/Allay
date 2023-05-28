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
public interface ItemGlass extends ItemStack {
    ItemType<ItemGlass> TYPE = ItemTypeBuilder
            .builder(ItemGlass.class)
            .vanillaItem(VanillaItemId.GLASS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
