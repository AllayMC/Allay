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
public interface ItemCoralFanHang2 extends ItemStack {
    ItemType<ItemCoralFanHang2> TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang2.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG2, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
