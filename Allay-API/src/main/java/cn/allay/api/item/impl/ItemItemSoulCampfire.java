package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSoulCampfire extends ItemStack {
    ItemType<ItemItemSoulCampfire> TYPE = ItemTypeBuilder
            .builder(ItemItemSoulCampfire.class)
            .vanillaItem(VanillaItemId.ITEM_SOUL_CAMPFIRE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
