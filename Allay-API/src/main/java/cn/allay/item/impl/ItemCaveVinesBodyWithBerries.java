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
public interface ItemCaveVinesBodyWithBerries extends ItemStack {
    ItemType<ItemCaveVinesBodyWithBerries> TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesBodyWithBerries.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_BODY_WITH_BERRIES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
