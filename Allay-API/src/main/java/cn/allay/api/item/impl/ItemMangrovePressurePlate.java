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
public interface ItemMangrovePressurePlate extends ItemStack {
    ItemType<ItemMangrovePressurePlate> TYPE = ItemTypeBuilder
            .builder(ItemMangrovePressurePlate.class)
            .vanillaItem(VanillaItemId.MANGROVE_PRESSURE_PLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
