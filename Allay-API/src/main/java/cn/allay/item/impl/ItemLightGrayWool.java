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
public interface ItemLightGrayWool extends ItemStack {
    ItemType<ItemLightGrayWool> TYPE = ItemTypeBuilder
            .builder(ItemLightGrayWool.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_WOOL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
