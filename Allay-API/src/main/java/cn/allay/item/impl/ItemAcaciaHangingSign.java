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
public interface ItemAcaciaHangingSign extends ItemStack {
    ItemType<ItemAcaciaHangingSign> TYPE = ItemTypeBuilder
            .builder(ItemAcaciaHangingSign.class)
            .vanillaItem(VanillaItemId.ACACIA_HANGING_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
