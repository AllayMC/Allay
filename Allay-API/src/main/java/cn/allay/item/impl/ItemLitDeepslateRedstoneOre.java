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
public interface ItemLitDeepslateRedstoneOre extends ItemStack {
    ItemType<ItemLitDeepslateRedstoneOre> TYPE = ItemTypeBuilder
            .builder(ItemLitDeepslateRedstoneOre.class)
            .vanillaItem(VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
