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
public interface ItemMangrovePressurePlate extends ItemStack {
    ItemType<ItemMangrovePressurePlate> TYPE = ItemTypeBuilder
            .builder(ItemMangrovePressurePlate.class)
            .vanillaItem(VanillaItemId.MANGROVE_PRESSURE_PLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
