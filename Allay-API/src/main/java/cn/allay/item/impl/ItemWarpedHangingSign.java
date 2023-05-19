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
public interface ItemWarpedHangingSign extends ItemStack {
    ItemType<ItemWarpedHangingSign> TYPE = ItemTypeBuilder
            .builder(ItemWarpedHangingSign.class)
            .vanillaItem(VanillaItemId.WARPED_HANGING_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
