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
public interface ItemDeepslateIronOre extends ItemStack {
    ItemType<ItemDeepslateIronOre> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateIronOre.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_IRON_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
