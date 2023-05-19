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
public interface ItemGoatHorn extends ItemStack {
    ItemType<ItemGoatHorn> TYPE = ItemTypeBuilder
            .builder(ItemGoatHorn.class)
            .vanillaItem(VanillaItemId.GOAT_HORN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
