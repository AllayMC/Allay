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
public interface ItemLightBlueWool extends ItemStack {
    ItemType<ItemLightBlueWool> TYPE = ItemTypeBuilder
            .builder(ItemLightBlueWool.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_WOOL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
