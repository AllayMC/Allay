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
public interface ItemCoralFanHang3 extends ItemStack {
    ItemType<ItemCoralFanHang3> TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang3.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG3, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
