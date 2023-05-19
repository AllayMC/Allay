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
public interface ItemLightGrayDye extends ItemStack {
    ItemType<ItemLightGrayDye> TYPE = ItemTypeBuilder
            .builder(ItemLightGrayDye.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_DYE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
