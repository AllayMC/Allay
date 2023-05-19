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
public interface ItemLitRedstoneOre extends ItemStack {
    ItemType<ItemLitRedstoneOre> TYPE = ItemTypeBuilder
            .builder(ItemLitRedstoneOre.class)
            .vanillaItem(VanillaItemId.LIT_REDSTONE_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
