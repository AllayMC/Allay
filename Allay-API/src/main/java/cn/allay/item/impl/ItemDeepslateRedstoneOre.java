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
public interface ItemDeepslateRedstoneOre extends ItemStack {
    ItemType<ItemDeepslateRedstoneOre> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateRedstoneOre.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_REDSTONE_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
