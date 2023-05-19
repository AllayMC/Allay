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
public interface ItemCoralFanHang extends ItemStack {
    ItemType<ItemCoralFanHang> TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
