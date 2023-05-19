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
public interface ItemLapisLazuli extends ItemStack {
    ItemType<ItemLapisLazuli> TYPE = ItemTypeBuilder
            .builder(ItemLapisLazuli.class)
            .vanillaItem(VanillaItemId.LAPIS_LAZULI, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
