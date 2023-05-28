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
public interface ItemDarkoakStandingSign extends ItemStack {
    ItemType<ItemDarkoakStandingSign> TYPE = ItemTypeBuilder
            .builder(ItemDarkoakStandingSign.class)
            .vanillaItem(VanillaItemId.DARKOAK_STANDING_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
