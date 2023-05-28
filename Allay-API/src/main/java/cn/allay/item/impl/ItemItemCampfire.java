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
public interface ItemItemCampfire extends ItemStack {
    ItemType<ItemItemCampfire> TYPE = ItemTypeBuilder
            .builder(ItemItemCampfire.class)
            .vanillaItem(VanillaItemId.ITEM_CAMPFIRE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
