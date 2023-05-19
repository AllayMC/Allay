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
public interface ItemSeaLantern extends ItemStack {
    ItemType<ItemSeaLantern> TYPE = ItemTypeBuilder
            .builder(ItemSeaLantern.class)
            .vanillaItem(VanillaItemId.SEA_LANTERN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
