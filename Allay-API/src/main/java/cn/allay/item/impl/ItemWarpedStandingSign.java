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
public interface ItemWarpedStandingSign extends ItemStack {
    ItemType<ItemWarpedStandingSign> TYPE = ItemTypeBuilder
            .builder(ItemWarpedStandingSign.class)
            .vanillaItem(VanillaItemId.WARPED_STANDING_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
