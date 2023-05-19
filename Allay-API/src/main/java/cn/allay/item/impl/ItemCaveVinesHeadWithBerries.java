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
public interface ItemCaveVinesHeadWithBerries extends ItemStack {
    ItemType<ItemCaveVinesHeadWithBerries> TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesHeadWithBerries.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_HEAD_WITH_BERRIES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
