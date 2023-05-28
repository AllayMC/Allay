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
public interface ItemSoulSoil extends ItemStack {
    ItemType<ItemSoulSoil> TYPE = ItemTypeBuilder
            .builder(ItemSoulSoil.class)
            .vanillaItem(VanillaItemId.SOUL_SOIL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
