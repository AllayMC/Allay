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
public interface ItemDarkOakPressurePlate extends ItemStack {
    ItemType<ItemDarkOakPressurePlate> TYPE = ItemTypeBuilder
            .builder(ItemDarkOakPressurePlate.class)
            .vanillaItem(VanillaItemId.DARK_OAK_PRESSURE_PLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
