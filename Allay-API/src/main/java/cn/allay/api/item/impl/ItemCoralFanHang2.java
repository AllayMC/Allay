package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
