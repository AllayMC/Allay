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
public interface ItemShroomlight extends ItemStack {
    ItemType<ItemShroomlight> TYPE = ItemTypeBuilder
            .builder(ItemShroomlight.class)
            .vanillaItem(VanillaItemId.SHROOMLIGHT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
